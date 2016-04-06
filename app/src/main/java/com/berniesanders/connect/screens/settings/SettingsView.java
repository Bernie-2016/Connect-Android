package com.berniesanders.connect.screens.settings;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;

import com.berniesanders.connect.R;
import com.berniesanders.connect.application.ApplicationPreferences;
import com.berniesanders.connect.dagger.ActivityScope;
import com.berniesanders.connect.hook.ActivityHook;
import com.berniesanders.connect.hook.ActivityHookBuilder;
import com.berniesanders.connect.view.CheckBoxSettingView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

@ActivityScope
public class SettingsView {
    private final Resources mResources;
    private final ApplicationPreferences mApplicationPreferences;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.push_master)
    CheckBoxSettingView mPushMasterView;

    @Inject
    public SettingsView(final Resources resources, final ApplicationPreferences applicationPreferences) {
        mResources = resources;
        mApplicationPreferences = applicationPreferences;
    }

    public ActivityHook getActivityHook() {
        return new ActivityHookBuilder()
                .onCreate(this::onCreate)
                .build();
    }

    private void onCreate(final AppCompatActivity activity, final Bundle savedInstanceState) {
        activity.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.setContentView(R.layout.activity_settings);
        ButterKnife.bind(this, activity);

        mToolbar.setTitle(R.string.settings_title);
        mToolbar.setTitleTextColor(mResources.getColor(android.R.color.white));
        mToolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        mToolbar.setNavigationOnClickListener(view -> activity.finish());

        mPushMasterView.configure(mApplicationPreferences.isPushEnabled(), mApplicationPreferences::setPushEnabled);
        mPushMasterView.setTitle(R.string.push_master_title);
    }
}
