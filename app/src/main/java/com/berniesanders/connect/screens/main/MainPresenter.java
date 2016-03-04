package com.berniesanders.connect.screens.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.berniesanders.connect.R;
import com.berniesanders.connect.dagger.ActivityScope;
import com.berniesanders.connect.hook.ActivityHook;
import com.berniesanders.connect.hook.ActivityHookBuilder;
import com.berniesanders.connect.route.ActionAlertRouter;
import com.berniesanders.connect.rx.ActivitySubscriptionManager;

import java.util.Arrays;
import java.util.Collection;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action0;
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
        whenAgree(activity, mView.onAgreeToPrivacy(), mModel::agreeToPrivacy);

        mView.getDrawerController().setMenuItemListener(itemId -> {
            mView.getDrawerController().close();

            switch (itemId) {
                case R.id.feedback:
                    break;
                case R.id.about:
                    break;
                case R.id.privacy_policy:
                    mView.showPrivacy(true);
                    break;
            }

            return false;
        });

        mSubscriptionManager.subscribe(mView.getSelectedActionAlerts(),
                actionAlert -> new ActionAlertRouter(actionAlert).selectAction().call(activity),
                error -> Timber.e(error, "selection action alert"));

        render();
    }

    private void render() {
        if (mModel.hasNotAgreedToPrivacy()) {
            mView.showPrivacy(false);
        } else {
            mSubscriptionManager.subscribe(mModel.getActionAlerts(),
                    mView::setActionAlerts,
                    error -> Timber.e(error, "action alerts"));
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
