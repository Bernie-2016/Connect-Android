package com.berniesanders.connect.dagger;

import android.content.Context;

import com.berniesanders.connect.ConnectApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final ConnectApplication mApplication;

    public ApplicationModule(final ConnectApplication application) {
        mApplication = application;
    }

    @Provides
    public Context provideContext() {
        return mApplication;
    }
}
