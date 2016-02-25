package com.berniesanders.connect.screens.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.berniesanders.connect.dagger.ActivityScope;
import com.berniesanders.connect.data.ActionAlert;
import com.berniesanders.connect.hook.ActivityHook;
import com.berniesanders.connect.hook.ActivityHookBuilder;
import com.berniesanders.connect.model.ActionAlertsModel;

import javax.inject.Inject;

@ActivityScope
public class DetailModel {
    public static final String KEY_ACTION_ALERT_ID = "action-alert-id";

    private final ActionAlertsModel mActionAlertsModel;

    private ActionAlert mActionAlert;

    @Inject
    public DetailModel(final ActionAlertsModel actionAlertsModel) {
        mActionAlertsModel = actionAlertsModel;
    }

    public ActivityHook getActivityHook() {
        return new ActivityHookBuilder()
                .onCreate(this::onCreate)
                .build();
    }

    private void onCreate(final AppCompatActivity activity, final Bundle savedInstanceState) {
        final String id = activity.getIntent().getStringExtra(DetailModel.KEY_ACTION_ALERT_ID);

        mActionAlert = mActionAlertsModel.getActionAlertById(id);
    }

    public ActionAlert getActionAlert() {
        return mActionAlert;
    }
}
