package com.berniesanders.connect.application.dagger;

import android.content.res.Resources;

import com.berniesanders.connect.application.ApplicationPreferences;
import com.berniesanders.connect.application.ConnectApplication;
import com.berniesanders.connect.model.ActionAlertsManager;
import com.berniesanders.connect.model.NewsFeedManager;
import com.berniesanders.connect.notification.NotificationController;
import com.berniesanders.connect.util.DimensionUtil;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(ConnectApplication connectApplication);

    ApplicationPreferences getApplicationPreferences();
    ActionAlertsManager getActionAlertsManager();
    NewsFeedManager getNewsFeedManager();
    NotificationController getNotificationController();
    Resources getResources();
    DimensionUtil getDimensionUtil();
}
