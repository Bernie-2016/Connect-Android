package com.berniesanders.connect.application.dagger;

import com.berniesanders.connect.model.ActionAlertsModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    ActionAlertsModel getActionAlertsModel();
}
