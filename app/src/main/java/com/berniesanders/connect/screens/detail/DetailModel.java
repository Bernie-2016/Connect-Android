package com.berniesanders.connect.screens.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.berniesanders.connect.dagger.ActivityScope;
import com.berniesanders.connect.data.ActionAlert;
import com.berniesanders.connect.hook.ActivityHook;
import com.berniesanders.connect.hook.ActivityHookBuilder;

import javax.inject.Inject;

@ActivityScope
public class DetailModel {
    public static final String KEY_ACTION_ALERT = "action-alert";

    private ActionAlert mActionAlert;

    @Inject
    public DetailModel() {
    }

    public ActivityHook getActivityHook() {
        return new ActivityHookBuilder()
                .onCreate(this::onCreate)
                .build();
    }

    private void onCreate(final AppCompatActivity activity, final Bundle savedInstanceState) {
        mActionAlert = activity.getIntent().getParcelableExtra(DetailModel.KEY_ACTION_ALERT);
    }

    public ActionAlert getActionAlert() {
        return mActionAlert;
    }
}
