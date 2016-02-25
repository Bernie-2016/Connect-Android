package com.berniesanders.connect.screens.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.berniesanders.connect.dagger.ActivityScope;
import com.berniesanders.connect.screens.detail.DetailActivity;
import com.berniesanders.connect.screens.detail.DetailModel;
import com.berniesanders.connect.hook.ActivityHook;
import com.berniesanders.connect.hook.ActivityHookBuilder;
import com.berniesanders.connect.hook.HasActivityHooks;

import java.util.Arrays;
import java.util.Collection;

import javax.inject.Inject;

import timber.log.Timber;

@ActivityScope
public class MainPresenter implements HasActivityHooks {
    private final MainModel mModel;
    private final MainView mView;

    @Inject
    public MainPresenter(final MainModel model, final MainView view) {
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
                        .onDestroy(this::onDestroy)
                        .build());
    }

    private void onCreate(final AppCompatActivity activity, final Bundle savedInstanceState) {
        mView.getSelectedActionAlerts().subscribe(
                actionAlert -> {
                    final Intent intent = new Intent(activity, DetailActivity.class);

                    intent.putExtra(DetailModel.KEY_ACTION_ALERT_ID, actionAlert.id());
                    activity.startActivity(intent);
                },
                error -> Timber.e(error, "selection action alert"));

        mModel.getActionAlerts().subscribe(
                mView::setActionAlerts,
                error -> Timber.e(error, "action alerts"));
    }

    private void onDestroy(final AppCompatActivity activity) {

    }
}
