package com.berniesanders.connect.model;

import com.annimon.stream.Optional;
import com.berniesanders.connect.api.ConnectApi;
import com.berniesanders.connect.data.ActionAlert;
import com.berniesanders.connect.data.ActionAlertGson;
import com.berniesanders.connect.db.GsonValueStore;
import com.berniesanders.connect.gson.JsonApiResponse;
import com.berniesanders.connect.db.GsonDb;
import com.berniesanders.connect.util.TimeToLive;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class ActionAlertsManager {
    private static final int SOFT_MAX_ALERTS = 12;

    private final MergingModelManager<JsonApiResponse, ActionAlertGson, ActionAlert, String> mDelegate;

    @Inject
    public ActionAlertsManager(final ConnectApi connectApi, final GsonDb gsonDb) {
        mDelegate = new MergingModelManager<>(
                SOFT_MAX_ALERTS,
                new GsonValueStore<>(gsonDb, GsonDb.ACTION_ALERTS, new TypeToken<List<ActionAlertGson>>() {}),
                new TimeToLive(TimeUnit.MINUTES, 10),
                ActionAlertGson::fromValue,
                ActionAlertGson::toValue,
                ActionAlert::id,
                connectApi::getActionAlerts,
                observable -> observable.map(JsonApiResponse::getActionAlerts));
    }

    public Optional<ActionAlert> getActionAlertById(final String id) {
        return mDelegate.getDataById(id);
    }

    public Observable<List<ActionAlert>> getActionAlerts() {
        return mDelegate.getData();
    }

    public Observable<List<ActionAlert>> requestActionAlerts() {
        return mDelegate.request();
    }
}
