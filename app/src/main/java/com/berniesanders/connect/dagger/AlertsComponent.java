package com.berniesanders.connect.dagger;

import com.berniesanders.connect.screens.alert.AlertsPresenter;
import com.berniesanders.connect.screens.alert.AlertsView;

import dagger.Component;

@AlertsScope
@Component(dependencies = ActivityComponent.class, modules = AlertsModule.class)
public interface AlertsComponent {
    AlertsView getAlertsView();
    AlertsPresenter getAlertsPresenter();
}
