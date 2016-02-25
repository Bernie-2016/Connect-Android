package com.berniesanders.connect.application;

import android.app.Application;

import com.berniesanders.connect.BuildConfig;
import com.berniesanders.connect.application.dagger.ApplicationComponent;
import com.berniesanders.connect.application.dagger.ApplicationModule;
import com.berniesanders.connect.application.dagger.DaggerApplicationComponent;

import timber.log.Timber;

public class ConnectApplication extends Application {
    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getObjectGraph() {
        return component;
    }
}
