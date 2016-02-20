package com.berniesanders.connect.model;

import com.annimon.stream.Optional;
import com.berniesanders.connect.api.ConnectApi;
import com.berniesanders.connect.data.ActionAlert;
import com.berniesanders.connect.gson.JsonApiResponse;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Singleton
public class ActionAlertsModel {
    private final ConnectApi mConnectApi;

    private Optional<List<ActionAlert>> mActionAlerts = Optional.empty();

    @Inject
    public ActionAlertsModel(final ConnectApi connectApi) {
        mConnectApi = connectApi;
    }

    public Observable<List<ActionAlert>> getActionAlerts() {
        return mActionAlerts
                .map(Observable::just)
                .orElse(requestActionAlerts());
    }

    public Observable<List<ActionAlert>> requestActionAlerts() {
        return mConnectApi.getActionAlerts()
                .map(JsonApiResponse::getActionAlerts)
                .flatMap(Observable::from)
                .map(idValue -> idValue.second)
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .cache()
                .doOnNext(actionAlerts -> {
                    mActionAlerts = Optional.of(actionAlerts);
                });
    }
}
