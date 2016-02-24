package com.berniesanders.connect.model;

import com.annimon.stream.Collectors;
import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.berniesanders.connect.api.ConnectApi;
import com.berniesanders.connect.data.ActionAlert;
import com.berniesanders.connect.gson.JsonApiResponse;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ActionAlertsModel {
    private final ConnectApi mConnectApi;

    private Optional<List<ActionAlert>> mActionAlerts = Optional.empty();
    private Map<String, ActionAlert> mActionAlertById = Collections.emptyMap();

    public ActionAlertsModel(final ConnectApi connectApi) {
        mConnectApi = connectApi;
    }

    public Optional<ActionAlert> getActionAlertById(final String id) {
        return Optional.ofNullable(mActionAlertById.get(id));
    }

    public Observable<List<ActionAlert>> getActionAlerts() {
        return mActionAlerts
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
                    mActionAlerts = Optional.of(actionAlerts);

                    mActionAlertById = Stream.of(actionAlerts)
                            .collect(Collectors.toMap(ActionAlert::id, alert -> alert));
                });
    }
}
