package com.berniesanders.connect.screens.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.berniesanders.connect.dagger.ActivityScope;
import com.berniesanders.connect.hook.ActivityHook;
import com.berniesanders.connect.hook.ActivityHookBuilder;
import com.berniesanders.connect.hook.HasActivityHooks;

import java.util.Arrays;
import java.util.Collection;

import javax.inject.Inject;

@ActivityScope
public class DetailPresenter implements HasActivityHooks {
    private final DetailModel mModel;
    private final DetailView mView;

    @Inject
    public DetailPresenter(final DetailModel model, final DetailView view) {
        mModel = model;
        mView = view;
    }

    @Override
    public Collection<ActivityHook> getActivityHooks() {
        return Arrays.asList(
                mView.getActivityHook(),
                mModel.getActivityHook(),
                new ActivityHookBuilder()
                        .onCreate(this::onCreate)
                        .build());
    }

    private void onCreate(final AppCompatActivity activity, final Bundle savedInstanceState) {
        mView.setActionAlert(mModel.getActionAlert());
    }
}
