package com.berniesanders.connect.model;

import com.annimon.stream.Collectors;
import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;
import com.annimon.stream.function.Supplier;
import com.berniesanders.connect.db.ValueStore;
import com.berniesanders.connect.util.StreamUtil;
import com.berniesanders.connect.util.TimeToLive;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MergingModelManager<Remote, DB, Local, ID> {
    private final int mSoftLimit;
    private final ValueStore<List<DB>> mValueStore;
    private final TimeToLive mTimeToLive;
    private final Function<Local, DB> mValueToDb;
    private final Function<DB, Local> mDbToValue;
    private final Function<Local, ID> mValueToId;
    private final Supplier<Observable<Remote>> mRequester;
    private final Function<Observable<Remote>, Observable<List<Local>>> mParser;

    private List<Local> mData = Collections.emptyList();
    private Map<ID, Local> mDataById = Collections.emptyMap();
    private boolean mLoadedData;

    public MergingModelManager(final int softLimit,
                               final ValueStore<List<DB>> valueStore,
                               final TimeToLive timeToLive,
                               final Function<Local, DB> valueToDb,
                               final Function<DB, Local> dbToValue,
                               final Function<Local, ID> valueToId,
                               final Supplier<Observable<Remote>> requester,
                               final Function<Observable<Remote>, Observable<List<Local>>> parser) {
        mSoftLimit = softLimit;
        mValueStore = valueStore;
        mTimeToLive = timeToLive;
        mValueToDb = valueToDb;
        mDbToValue = dbToValue;
        mValueToId = valueToId;
        mRequester = requester;
        mParser = parser;
    }

    public Observable<List<Local>> getData() {
        return mTimeToLive.ifValid(mData)
                .map(Observable::just)
                .orElseGet(this::request);
    }

    public Optional<Local> getDataById(final ID id) {
        return Optional.ofNullable(mDataById.get(id));
    }

    private void setData(final List<Local> actionAlerts) {
        mData = Collections.unmodifiableList(actionAlerts);
        mDataById = Stream.of(mData).collect(Collectors.toMap(mValueToId, value -> value));
    }

    public Observable<List<Local>> request() {
        return mParser.apply(mRequester.get().subscribeOn(Schedulers.io()))
                .zipWith(getDbData(), (newData, dbData) -> concatData(newData, dbData.orElse(mData)))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(resultData -> {
                    if (!mData.equals(resultData)) {
                        setData(resultData);

                        mValueStore.write(Stream.of(resultData)
                                .map(mValueToDb)
                                .collect(Collectors.toList()));
                    }

                    mTimeToLive.reset();
                })
                .cache();
    }

    private List<Local> concatData(final List<Local> newData, final List<Local> oldData) {
        return StreamUtil.concatKeepFirst(newData, oldData, mValueToId)
                .limit(Math.max(mSoftLimit, newData.size()))
                .collect(Collectors.toList());
    }

    private Observable<Optional<List<Local>>> getDbData() {
        if (mLoadedData) {
            return Observable.just(Optional.empty());
        } else {
            mLoadedData = true;

            return mValueStore.read()
                    .map(optional -> optional.map(list -> Stream.of(list)
                            .map(mDbToValue)
                            .collect(Collectors.toList())));
        }
    }
}
