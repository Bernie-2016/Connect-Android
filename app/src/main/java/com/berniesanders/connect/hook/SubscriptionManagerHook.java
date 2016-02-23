package com.berniesanders.connect.hook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.berniesanders.connect.rx.SubscriptionManager;

import timber.log.Timber;

public class SubscriptionManagerHook implements ActivityHook {

    private final SubscriptionManager manager;

    public SubscriptionManagerHook(SubscriptionManager manager) {
        this.manager = manager;
    }

    @Override
    public void onCreate(AppCompatActivity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onDestroy(AppCompatActivity activity) {
        Timber.d("unsubscribing from activity: " + activity.getClass().getSimpleName());
        manager.unsubscribeAll();
    }

    @Override
    public void onPause(AppCompatActivity activity) {

    }

    @Override
    public void onResume(AppCompatActivity activity) {

    }

    @Override
    public void onSaveInstanceState(AppCompatActivity activity, Bundle outState) {

    }
}
