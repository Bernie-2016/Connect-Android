package com.berniesanders.connect;

import android.app.Application;

import com.berniesanders.connect.dagger.ApplicationComponent;
import com.berniesanders.connect.dagger.ApplicationModule;
import com.berniesanders.connect.dagger.DaggerApplicationComponent;

import timber.log.Timber;

public class ConnectApplication extends Application {
    private static ApplicationComponent sComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        sComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static ApplicationComponent component() {
        return sComponent;
    }
}
