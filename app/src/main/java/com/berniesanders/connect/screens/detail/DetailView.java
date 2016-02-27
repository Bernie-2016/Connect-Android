package com.berniesanders.connect.screens.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.FrameLayout;

import com.berniesanders.connect.R;
import com.berniesanders.connect.dagger.ActivityScope;
import com.berniesanders.connect.data.ActionAlert;
import com.berniesanders.connect.hook.ActivityHook;
import com.berniesanders.connect.hook.ActivityHookBuilder;
import com.berniesanders.connect.view.ActionAlertDetailsView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

@ActivityScope
public class DetailView {
    private ActionAlertDetailsView mActionAlertDetailsView;

    @Bind(value = R.id.root)
    FrameLayout mRoot;

    @Inject
    public DetailView() {
    }

    public ActivityHook getActivityHook() {
        return new ActivityHookBuilder()
                .onCreate(this::onCreate)
                .build();
    }

    private void onCreate(final AppCompatActivity activity, final Bundle savedInstanceState) {
        activity.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.setContentView(R.layout.activity_detail);
        ButterKnife.bind(this, activity);

        mActionAlertDetailsView = new ActionAlertDetailsView(activity);
        mRoot.addView(mActionAlertDetailsView);
    }

    public void setActionAlert(final ActionAlert actionAlert) {
        mActionAlertDetailsView.setActionAlert(actionAlert);
    }
}
