package com.berniesanders.connect.application;

import android.app.Application;

import com.berniesanders.connect.BuildConfig;
import com.berniesanders.connect.application.dagger.ApplicationComponent;
import com.berniesanders.connect.application.dagger.ApplicationModule;
import com.berniesanders.connect.application.dagger.DaggerApplicationComponent;
import com.berniesanders.connect.parse.ParseManager;

import javax.inject.Inject;

import timber.log.Timber;

public class ConnectApplication extends Application {
    private ApplicationComponent mComponent;

    @Inject
    ParseManager mParseManager;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        mComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        mComponent.inject(this);

        mParseManager.init();
    }

    public ApplicationComponent getObjectGraph() {
        return mComponent;
    }
}
