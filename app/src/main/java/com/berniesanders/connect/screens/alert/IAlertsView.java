package com.berniesanders.connect.screens.alert;

import com.berniesanders.connect.dagger.AlertsScope;
import com.berniesanders.connect.data.ActionAlert;

import java.util.List;

import rx.Observable;

@AlertsScope
public interface IAlertsView {
    void setActionAlerts(List<ActionAlert> actionAlerts);

    Observable<ActionAlert> getSelectedActionAlerts();
}
