package com.berniesanders.connect.screens.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.berniesanders.connect.R;
import com.berniesanders.connect.dagger.ActivityScope;
import com.berniesanders.connect.hook.ActivityHook;
import com.berniesanders.connect.hook.ActivityHookBuilder;
import com.berniesanders.connect.rx.ActivitySubscriptionManager;
import com.berniesanders.connect.screen.ViewScreenManager;
import com.berniesanders.connect.screens.alert.AlertsScreen;

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
    private final ActivitySubscriptionManager mSubscriptionManager;

    @Inject
    public MainPresenter(final MainModel model, final MainView view, final ViewScreenManager viewScreenManager, final AlertsScreen alertsScreen, final ActivitySubscriptionManager subscriptionManager) {
        mModel = model;
        mView = view;
        mViewScreenManager = viewScreenManager;
        mAlertsScreen = alertsScreen;
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
                    break;
                case R.id.news:
                    break;
                case R.id.nearby:
                    break;
                case R.id.privacy_policy:
                    mView.showPrivacy(true);
                    break;
                case R.id.settings:
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
