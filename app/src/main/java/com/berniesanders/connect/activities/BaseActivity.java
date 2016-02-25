package com.berniesanders.connect.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.berniesanders.connect.application.ConnectApplication;
import com.berniesanders.connect.dagger.ActivityComponent;
import com.berniesanders.connect.dagger.ActivityModule;
import com.berniesanders.connect.dagger.DaggerActivityComponent;
import com.berniesanders.connect.hook.ActivityHook;

public abstract class BaseActivity extends AppCompatActivity {

    private Iterable<ActivityHook> mHooks;
    private ActivityComponent mComponent;

    protected abstract Iterable<ActivityHook> getHooks();

    public ActivityComponent getObjectGraph() {
        if (mComponent == null) {
            mComponent = DaggerActivityComponent.builder()
                    .applicationComponent(((ConnectApplication) getApplication()).getObjectGraph())
                    .activityModule(new ActivityModule(this))
                    .build();
        }

        return mComponent;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        mHooks = getHooks();

        super.onCreate(savedInstanceState);

        for (final ActivityHook hook : mHooks) {
            hook.onCreate(this, savedInstanceState);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        for (final ActivityHook hook : mHooks) {
            hook.onDestroy(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        for (final ActivityHook hook : mHooks) {
            hook.onResume(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        for (final ActivityHook hook : mHooks) {
            hook.onPause(this);
        }
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);

        for (final ActivityHook hook : mHooks) {
            hook.onSaveInstanceState(this, outState);
        }
    }
}
