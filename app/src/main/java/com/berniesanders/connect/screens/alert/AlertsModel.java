package com.berniesanders.connect.screens.alert;

import com.berniesanders.connect.dagger.AlertsScope;
import com.berniesanders.connect.data.ActionAlert;
import com.berniesanders.connect.model.ActionAlertsManager;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

@AlertsScope
public class AlertsModel {
    private final ActionAlertsManager mActionAlertsManager;

    @Inject
    public AlertsModel(final ActionAlertsManager actionAlertsManager) {
        mActionAlertsManager = actionAlertsManager;
    }

    public Observable<List<ActionAlert>> getActionAlerts() {
        return mActionAlertsManager.getActionAlerts();
    }
}
