package com.berniesanders.connect;

import android.app.Application;
import android.content.Context;

import com.berniesanders.connect.dagger.ApplicationComponent;
import com.berniesanders.connect.dagger.ApplicationModule;
import com.berniesanders.connect.dagger.DaggerApplicationComponent;

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
