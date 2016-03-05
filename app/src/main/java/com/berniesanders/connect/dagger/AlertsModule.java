package com.berniesanders.connect.dagger;

import com.berniesanders.connect.screens.alert.AlertsView;
import com.berniesanders.connect.screens.alert.IAlertsView;

import dagger.Module;
import dagger.Provides;

@Module
public class AlertsModule {
    public AlertsModule() {
    }

    @Provides
    public IAlertsView provideAlertsView(final AlertsView alertsView) {
        return alertsView;
    }
}
