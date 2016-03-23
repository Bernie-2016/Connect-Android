package com.berniesanders.connect.screens.main;

import android.support.v7.app.AppCompatActivity;

import com.berniesanders.connect.application.ApplicationPreferences;
import com.berniesanders.connect.dagger.ActivityScope;
import com.berniesanders.connect.hook.ActivityHook;
import com.berniesanders.connect.hook.ActivityHookBuilder;
import com.berniesanders.connect.http.AlertServer;

import java.io.IOException;

import javax.inject.Inject;

import timber.log.Timber;

@ActivityScope
public class MainModel {
    private final ApplicationPreferences mApplicationPreferences;
    private final AlertServer mAlertServer;

    @Inject
    public MainModel(final ApplicationPreferences applicationPreferences, final AlertServer alertServer) {
        mApplicationPreferences = applicationPreferences;
        mAlertServer = alertServer;
    }

    public ActivityHook getActivityHook() {
        return new ActivityHookBuilder()
                .onResume(this::onResume)
                .onPause(this::onPause)
                .build();
    }

    private void onResume(final AppCompatActivity activity) {
        try {
            mAlertServer.start();
        } catch (final IOException exception) {
            Timber.e(exception, "");
        }
    }

    private void onPause(final AppCompatActivity activity) {
        mAlertServer.stop();
    }

    public boolean hasNotAgreedToTerms() {
        return !mApplicationPreferences.hasAgreedToTerms();
    }

    public void agreeToTerms() {
        mApplicationPreferences.agreeToTerms();
    }

    public boolean hasNotAgreedToPrivacy() {
        return !mApplicationPreferences.hasAgreedToPrivacy();
    }

    public void agreeToPrivacy() {
        mApplicationPreferences.agreeToPrivacy();
    }
}
