package com.berniesanders.connect.dagger;

import android.app.Activity;

import com.berniesanders.connect.model.ActionAlertsManager;
import com.berniesanders.connect.rx.ActivitySubscriptionManager;
import com.berniesanders.connect.screen.ViewScreenFactory;
import com.berniesanders.connect.screens.main.MainActivity;
import com.berniesanders.connect.application.dagger.ApplicationComponent;
import com.berniesanders.connect.screens.detail.DetailActivity;
import com.berniesanders.connect.util.DimensionUtil;

import javax.inject.Named;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(final MainActivity activity);
    void inject(final DetailActivity activity);

    // application
    ActionAlertsManager getActionAlertsManager();
    DimensionUtil getDimensionUtil();

    // activity
    Activity getActivity();
    ViewScreenFactory getViewScreenFactory();
    ActivitySubscriptionManager getActivitySubscriptionManager();
    @Named(Name.CONTAINER) int getContainerId();
}
