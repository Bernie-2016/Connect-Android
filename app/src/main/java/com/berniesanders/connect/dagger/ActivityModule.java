package com.berniesanders.connect.dagger;

import android.app.Activity;
import android.content.Context;

import com.berniesanders.connect.activities.BaseActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private final BaseActivity mActivity;

    public ActivityModule(final BaseActivity activity) {
        mActivity = activity;
    }

    @Provides
    public Context provideContext() {
        return mActivity;
    }

    @Provides
    public Activity provideActivity() {
        return mActivity;
    }

    @Provides
    public BaseActivity provideAppCompatActivity() {
        return mActivity;
    }
}
