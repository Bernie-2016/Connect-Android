package com.berniesanders.connect.rx;

import android.app.Activity;

public class ActivitySubscriptionManager extends SubscriptionManager<Activity> {
    public ActivitySubscriptionManager(Activity instance) {
        super(instance);
    }

    @Override
    protected boolean validate(Activity activity) {
        return !activity.isFinishing();
    }
}
