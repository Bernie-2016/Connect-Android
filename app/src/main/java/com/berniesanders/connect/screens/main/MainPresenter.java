package com.berniesanders.connect.screens.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.berniesanders.connect.R;
import com.berniesanders.connect.dagger.ActivityScope;
import com.berniesanders.connect.hook.ActivityHook;
import com.berniesanders.connect.hook.ActivityHookBuilder;
import com.berniesanders.connect.rx.ActivitySubscriptionManager;
import com.berniesanders.connect.screen.ViewScreenManager;
import com.berniesanders.connect.screens.alert.AlertsScreen;
import com.berniesanders.connect.screens.news.NewsScreen;
import com.berniesanders.connect.screens.settings.SettingsActivity;

import java.util.Arrays;
import java.util.Collection;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action0;

@ActivityScope
public class MainPresenter {
    private final MainModel mModel;
    private final MainView mView;
    private final ViewScreenManager mViewScreenManager;
    private final AlertsScreen mAlertsScreen;
    private final NewsScreen mNewsScreen;
    private final ActivitySubscriptionManager mSubscriptionManager;

    @Inject
    public MainPresenter(final MainModel model, final MainView view, final ViewScreenManager viewScreenManager, final AlertsScreen alertsScreen, final NewsScreen newsScreen, final ActivitySubscriptionManager subscriptionManager) {
        mModel = model;
        mView = view;
        mViewScreenManager = viewScreenManager;
        mAlertsScreen = alertsScreen;
        mNewsScreen = newsScreen;
        mSubscriptionManager = subscriptionManager;
    }

    public Collection<ActivityHook> getActivityHooks() {
        return Arrays.asList(
                mSubscriptionManager.getActivityHook(),
                mView.getActivityHook(),
                mModel.getActivityHook(),
                mViewScreenManager.getActivityHook(),
                new ActivityHookBuilder()
                        .onCreate(this::onCreate)
                        .build());
    }

    private void onCreate(final AppCompatActivity activity, final Bundle savedInstanceState) {
        whenAgree(activity, mView.onAgreeToPrivacy(), mModel::agreeToPrivacy);

        mView.getDrawerView().setMenuItemListener(itemId -> {
            mView.getDrawerView().close();

            switch (itemId) {
                case R.id.act_now:
                    mViewScreenManager.switchTo(mAlertsScreen);
                    mView.selectScreen(0);
                    break;
                case R.id.news:
                    mViewScreenManager.switchTo(mNewsScreen);
                    mView.selectScreen(1);
                    break;
                case R.id.nearby:
                    break;
                case R.id.privacy_policy:
                    mView.showPrivacy(true);
                    break;
                case R.id.settings:
                    activity.startActivity(new Intent(activity, SettingsActivity.class));
                    break;
            }

            return false;
        });

        render();
    }

    private void render() {
        if (mModel.hasNotAgreedToPrivacy()) {
            mView.showPrivacy(false);
        } else {
            mViewScreenManager.switchTo(mAlertsScreen);
        }
    }

    private void whenAgree(final AppCompatActivity activity, final Observable<Boolean> agreeObservable, final Action0 onAgree) {
        mSubscriptionManager.subscribe(
                agreeObservable.onErrorResumeNext(Observable.just(false)),
                didAgree -> {
                    if (didAgree) {
                        onAgree.call();
                        render();
                    } else {
                        activity.finish();
                    }
                });
    }
}
