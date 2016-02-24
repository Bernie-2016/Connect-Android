package com.berniesanders.connect;

import com.berniesanders.connect.hook.ActivityHook;
import com.berniesanders.connect.hook.ActivityHookBuilder;

import timber.log.Timber;

public class ResumePauseLogger {
    public static ActivityHook createHook() {
        return new ActivityHookBuilder()
                .onResume(activity -> Timber.v("resume: " + activity.getClass().getSimpleName()))
                .onPause(activity -> Timber.v("pause: " + activity.getClass().getSimpleName()))
                .build();
    }
}
