package com.berniesanders.connect.model;

import com.annimon.stream.Collectors;
import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.berniesanders.connect.api.ConnectApi;
import com.berniesanders.connect.data.ActionAlert;
import com.berniesanders.connect.data.ActionAlertGson;
import com.berniesanders.connect.gson.JsonApiResponse;
import com.berniesanders.connect.util.GsonDb;
import com.berniesanders.connect.util.TimeToLive;
import com.google.gson.reflect.TypeToken;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ActionAlertsManager {
    private final ConnectApi mConnectApi;
    private final TimeToLive mTimeToLive = new TimeToLive(TimeUnit.MINUTES, 2);
    private final GsonDb mGsonDb;

    private List<ActionAlert> mActionAlerts = Collections.emptyList();
    private Map<String, ActionAlert> mActionAlertsById = Collections.emptyMap();
    private boolean mLoadedActionAlerts;

    public ActionAlertsManager(final ConnectApi connectApi, final GsonDb gsonDb) {
        mConnectApi = connectApi;
        mGsonDb = gsonDb;
    }

    private void setActionAlerts(final List<ActionAlert> actionAlerts) {
        mActionAlerts = Collections.unmodifiableList(actionAlerts);
        mActionAlertsById = Stream.of(mActionAlerts).collect(Collectors.toMap(ActionAlert::id, alert -> alert));
        mTimeToLive.reset();
    }

    public Optional<ActionAlert> getActionAlertById(final String id) {
        return Optional.ofNullable(mActionAlertsById.get(id));
    }

    public Observable<List<ActionAlert>> getActionAlerts() {
        return mTimeToLive.ifValid(mActionAlerts)
                .map(Observable::just)
                .orElseGet(this::requestActionAlerts);
    }

    private Observable<List<ActionAlert>> requestActionAlerts() {
        return getDbActionAlerts().flatMap(optionalAlerts -> optionalAlerts
                .map(dbAlerts -> {
                    setActionAlerts(dbAlerts);
                    return Observable.just(dbAlerts);
                })
                .orElse(mConnectApi.getActionAlerts()
                        .map(JsonApiResponse::getActionAlerts)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .cache()
                        .doOnNext(serverAlerts -> {
                            final Set<String> serverIds = Stream.of(serverAlerts)
                                    .map(ActionAlert::id)
                                    .collect(Collectors.toSet());

                            setActionAlerts(Stream.concat(
                                    Stream.of(serverAlerts),
                                    Stream.of(mActionAlerts).filter(value -> !serverIds.contains(value.id())))
                                    .collect(Collectors.toList()));

                            mGsonDb.write(GsonDb.ACTION_ALERTS, Stream.of(mActionAlerts)
                                    .map(ActionAlertGson::fromValue)
                                    .collect(Collectors.toList()));
                        })));
    }

    private Observable<Optional<List<ActionAlert>>> getDbActionAlerts() {
        if (mLoadedActionAlerts) {
            return Observable.just(Optional.empty());
        } else {
            mLoadedActionAlerts = true;

            return mGsonDb.read(GsonDb.ACTION_ALERTS, new TypeToken<List<ActionAlertGson>>() {})
                    .map(optional -> optional.map(list -> Stream.of(list)
                            .map(ActionAlertGson::toValue)
                            .collect(Collectors.toList())));
        }
    }
}
