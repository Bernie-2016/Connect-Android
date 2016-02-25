package com.berniesanders.connect.hook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ActivityHookBuilder {
    private ActivityOnCreate mOnCreate;
    private ActivityOnDestroy mOnDestroy;
    private ActivityOnResume mOnResume;
    private ActivityOnPause mOnPause;
    private ActivityOnSaveInstanceState mOnSaveInstanceState;

    public ActivityHookBuilder onPause(final ActivityOnPause onPause) {
        mOnPause = onPause;
        return this;
    }

    public ActivityHookBuilder onResume(final ActivityOnResume onResume) {
        mOnResume = onResume;
        return this;
    }

    public ActivityHookBuilder onDestroy(final ActivityOnDestroy onDestroy) {
        mOnDestroy = onDestroy;
        return this;
    }

    public ActivityHookBuilder onCreate(final ActivityOnCreate onCreate) {
        mOnCreate = onCreate;
        return this;
    }

    public ActivityHookBuilder onSaveInstanceState(final ActivityOnSaveInstanceState onSaveInstanceState) {
        mOnSaveInstanceState = onSaveInstanceState;
        return this;
    }

    public ActivityHook build() {
        return new ActivityHook() {
            @Override
            public void onCreate(final AppCompatActivity activity, final Bundle savedInstanceState) {
                if (mOnCreate != null) {
                    mOnCreate.onCreate(activity, savedInstanceState);
                }
            }

            @Override
            public void onDestroy(final AppCompatActivity activity) {
                if (mOnDestroy != null) {
                    mOnDestroy.onDestroy(activity);
                }
            }

            @Override
            public void onPause(final AppCompatActivity activity) {
                if (mOnPause != null) {
                    mOnPause.onPause(activity);
                }
            }

            @Override
            public void onResume(final AppCompatActivity activity) {
                if (mOnResume != null) {
                    mOnResume.onResume(activity);
                }
            }

            @Override
            public void onSaveInstanceState(final AppCompatActivity activity, final Bundle outState) {
                if (mOnSaveInstanceState != null) {
                    mOnSaveInstanceState.onSaveInstanceState(activity, outState);
                }
            }
        };
    }
}
