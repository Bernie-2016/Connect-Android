package com.berniesanders.connect.model;

import com.annimon.stream.Collectors;
import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.berniesanders.connect.api.ConnectApi;
import com.berniesanders.connect.data.ActionAlert;
import com.berniesanders.connect.gson.JsonApiResponse;
import com.berniesanders.connect.util.TimeToLive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ActionAlertsManager {
    private final ConnectApi mConnectApi;
    private final TimeToLive mTimeToLive = new TimeToLive(TimeUnit.MINUTES, 2);

    private List<ActionAlert> mActionAlerts = Collections.emptyList();
    private Map<String, ActionAlert> mActionAlertsById = Collections.emptyMap();

    public ActionAlertsManager(final ConnectApi connectApi) {
        mConnectApi = connectApi;
    }

    public Optional<ActionAlert> getActionAlertById(final String id) {
        return Optional.ofNullable(mActionAlertsById.get(id));
    }

    public Observable<List<ActionAlert>> getActionAlerts() {
        return mTimeToLive.ifValid(mActionAlerts)
                .map(Observable::just)
                .orElse(requestActionAlerts());
    }

    public Observable<List<ActionAlert>> requestActionAlerts() {
        return mConnectApi.getActionAlerts()
                .map(JsonApiResponse::getActionAlerts)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .cache()
                .doOnNext(actionAlerts -> {
                    mActionAlerts = Collections.unmodifiableList(new ArrayList<>(actionAlerts));
                    mActionAlertsById = Stream.of(mActionAlerts).collect(Collectors.toMap(ActionAlert::id, alert -> alert));
                    mTimeToLive.reset();
                });
    }
}
