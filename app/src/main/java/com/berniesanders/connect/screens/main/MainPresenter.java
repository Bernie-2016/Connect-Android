package com.berniesanders.connect.screens.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.berniesanders.connect.dagger.ActivityScope;
import com.berniesanders.connect.hook.ActivityHook;
import com.berniesanders.connect.hook.ActivityHookBuilder;
import com.berniesanders.connect.rx.ActivitySubscriptionManager;
import com.berniesanders.connect.screens.detail.DetailActivity;
import com.berniesanders.connect.screens.detail.DetailModel;

import java.util.Arrays;
import java.util.Collection;

import javax.inject.Inject;

import timber.log.Timber;

@ActivityScope
public class MainPresenter {
    private final MainModel mModel;
    private final MainView mView;
    private final ActivitySubscriptionManager mSubscriptionManager;

    @Inject
    public MainPresenter(final MainModel model, final MainView view, final ActivitySubscriptionManager subscriptionManager) {
        mModel = model;
        mView = view;
        mSubscriptionManager = subscriptionManager;
    }

    public Collection<ActivityHook> getActivityHooks() {
        return Arrays.asList(
                mSubscriptionManager.getActivityHook(),
                mView.getActivityHook(),
                mModel.getActivityHook(),
                new ActivityHookBuilder()
                        .onCreate(this::onCreate)
                        .build());
    }

    private void onCreate(final AppCompatActivity activity, final Bundle savedInstanceState) {
        mSubscriptionManager.subscribe(mView.getSelectedActionAlerts(),
                actionAlert -> {
                    final Intent intent = new Intent(activity, DetailActivity.class);

                    intent.putExtra(DetailModel.KEY_ACTION_ALERT_ID, actionAlert.id());
                    activity.startActivity(intent);
                },
                error -> Timber.e(error, "selection action alert"));

        mSubscriptionManager.subscribe(mModel.getActionAlerts(),
                mView::setActionAlerts,
                error -> Timber.e(error, "action alerts"));
    }
}
