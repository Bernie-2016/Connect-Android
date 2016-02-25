package com.berniesanders.connect.screens.detail;

import android.os.Bundle;

import com.berniesanders.connect.activities.BaseActivity;
import com.berniesanders.connect.hook.ActivityHook;

import javax.inject.Inject;

public class DetailActivity extends BaseActivity {
    @Inject
    DetailPresenter mPresenter;

    @Override
    protected Iterable<ActivityHook> getHooks() {
        return mPresenter.getActivityHooks();
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        getObjectGraph().inject(this);
        super.onCreate(savedInstanceState);
    }
}
