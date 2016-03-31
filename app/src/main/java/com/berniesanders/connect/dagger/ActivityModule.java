package com.berniesanders.connect.dagger;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.berniesanders.connect.R;
import com.berniesanders.connect.activities.BaseActivity;

import javax.inject.Named;

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
    public AppCompatActivity provideAppCompatActivity() {
        return mActivity;
    }

    @Provides
    public BaseActivity provideBaseActivity() {
        return mActivity;
    }

    @Named(Name.CONTAINER)
    @Provides
    public int provideContainerId() {
        return R.id.screen_container;
    }
}
