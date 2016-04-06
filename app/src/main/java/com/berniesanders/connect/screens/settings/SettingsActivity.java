package com.berniesanders.connect.screens.settings;

import android.os.Bundle;

import com.berniesanders.connect.activities.BaseActivity;
import com.berniesanders.connect.hook.ActivityHook;

import javax.inject.Inject;

public class SettingsActivity extends BaseActivity {
    @Inject
    SettingsPresenter mPresenter;

    @Override
    protected Iterable<ActivityHook> getHooks() {
        return mPresenter.getActivityHooks();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getObjectGraph().inject(this);
        super.onCreate(savedInstanceState);
    }
}
