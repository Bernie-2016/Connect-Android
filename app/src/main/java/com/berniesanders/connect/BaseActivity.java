package com.berniesanders.connect;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.berniesanders.connect.hook.ActivityHook;

public class BaseActivity extends AppCompatActivity {
    private Iterable<ActivityHook> mHooks;

    protected void setHooks(final Iterable<ActivityHook> hooks) {
        mHooks = hooks;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState, final PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

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
