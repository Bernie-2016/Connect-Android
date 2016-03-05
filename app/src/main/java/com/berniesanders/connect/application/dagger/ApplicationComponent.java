package com.berniesanders.connect.application.dagger;

import com.berniesanders.connect.application.ApplicationPreferences;
import com.berniesanders.connect.model.ActionAlertsManager;
import com.berniesanders.connect.util.DimensionUtil;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    ApplicationPreferences getApplicationPreferences();
    ActionAlertsManager getActionAlertsModel();
    DimensionUtil getDimensionUtil();
}
