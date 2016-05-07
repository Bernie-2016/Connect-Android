package com.berniesanders.connect.screens.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.berniesanders.connect.R;
import com.berniesanders.connect.dagger.ActivityScope;
import com.berniesanders.connect.hook.ActivityHook;
import com.berniesanders.connect.hook.ActivityHookBuilder;
import com.berniesanders.connect.rx.ActivitySubscriptionManager;
import com.berniesanders.connect.screen.Screen;
import com.berniesanders.connect.screen.ViewScreenManager;
import com.berniesanders.connect.screens.alert.AlertsScreen;
import com.berniesanders.connect.screens.news.NewsScreen;
import com.berniesanders.connect.screens.settings.SettingsActivity;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action0;

@ActivityScope
public class MainPresenter {
    private static final String KEY_SCREEN_INDEX = "SCREEN_INDEX";

    private final MainModel mModel;
    private final MainView mView;
    private final ViewScreenManager mViewScreenManager;
    private final AlertsScreen mAlertsScreen;
    private final NewsScreen mNewsScreen;
    private final ActivitySubscriptionManager mSubscriptionManager;
    private final Map<Screen<View>, Integer> mScreenToIndex = new HashMap<>();
    private final Map<Integer, Screen<View>> mIndexToScreen = new HashMap<>();

    private Integer mScreenIndex = 0;

    @Inject
    public MainPresenter(final MainModel model, final MainView view, final ViewScreenManager viewScreenManager, final AlertsScreen alertsScreen, final NewsScreen newsScreen, final ActivitySubscriptionManager subscriptionManager) {
        mModel = model;
        mView = view;
        mViewScreenManager = viewScreenManager;
        mAlertsScreen = alertsScreen;
        mNewsScreen = newsScreen;
        mSubscriptionManager = subscriptionManager;

        mScreenToIndex.put(mAlertsScreen, 0);
        mScreenToIndex.put(mNewsScreen, 1);

        mIndexToScreen.put(0, mAlertsScreen);
        mIndexToScreen.put(1, mNewsScreen);
    }

    public Collection<ActivityHook> getActivityHooks() {
        return Arrays.asList(
                mSubscriptionManager.getActivityHook(),
                mView.getActivityHook(),
                mModel.getActivityHook(),
                mViewScreenManager.getActivityHook(),
                new ActivityHookBuilder()
                        .onCreate(this::onCreate)
                        .onSaveInstanceState(this::onSaveInstanceState)
                        .build());
    }

    private void onCreate(final AppCompatActivity activity, final Bundle savedInstanceState) {
        whenAgree(activity, mView.onAgreeToPrivacy(), mModel::agreeToPrivacy);

        mView.getDrawerView().setMenuItemListener(itemId -> {
            mView.getDrawerView().close();

            switch (itemId) {
                case R.id.act_now:
                    switchTo(mAlertsScreen);
                    break;
                case R.id.news:
                    switchTo(mNewsScreen);
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

        if (savedInstanceState != null) {
            mScreenIndex = savedInstanceState.getInt(KEY_SCREEN_INDEX, 0);
        }

        render();
    }

    private void switchTo(final Screen<View> screen) {
        mViewScreenManager.switchTo(screen);
        mScreenIndex = mScreenToIndex.get(screen);
        mView.selectScreen(mScreenIndex);
    }

    private void onSaveInstanceState(final AppCompatActivity activity, final Bundle bundle) {
        bundle.putInt(KEY_SCREEN_INDEX, mScreenIndex);
    }

    private void render() {
        if (mModel.hasNotAgreedToPrivacy()) {
            mView.showPrivacy(false);
        } else {
            switchTo(mIndexToScreen.get(mScreenIndex));
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
