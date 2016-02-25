package com.berniesanders.connect.screens.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.berniesanders.connect.dagger.ActivityScope;
import com.berniesanders.connect.presenter.SubscribingPresenter;
import com.berniesanders.connect.screens.detail.DetailActivity;
import com.berniesanders.connect.screens.detail.DetailModel;
import com.berniesanders.connect.hook.ActivityHook;
import com.berniesanders.connect.hook.ActivityHookBuilder;
import com.berniesanders.connect.hook.HasActivityHooks;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

@ActivityScope
public class MainPresenter extends SubscribingPresenter {
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
                getSubscriptionManagingHookBuilder().build());
    }

    @Override
    protected void onCreate(final AppCompatActivity activity, final Bundle savedInstanceState) {
        super.onCreate(activity, savedInstanceState);
        subscribe(mView.getSelectedActionAlerts(),
                actionAlert -> {
                    final Intent intent = new Intent(activity, DetailActivity.class);

                    intent.putExtra(DetailModel.KEY_ACTION_ALERT_ID, actionAlert.id());
                    activity.startActivity(intent);
                },
                error -> Timber.e(error, "selection action alert"));

        subscribe(mModel.getActionAlerts(),
                mView::setActionAlerts,
                error -> Timber.e(error, "action alerts"));
    }
}
