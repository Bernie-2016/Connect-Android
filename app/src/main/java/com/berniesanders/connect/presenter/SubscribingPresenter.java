package com.berniesanders.connect.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.berniesanders.connect.hook.ActivityHook;
import com.berniesanders.connect.hook.ActivityHookBuilder;
import com.berniesanders.connect.hook.HasActivityHooks;
import com.berniesanders.connect.rx.ActivitySubscriptionManager;
import com.berniesanders.connect.rx.SubscriptionManager;

import java.util.Collection;

import rx.Observable;
import rx.Observer;
import rx.functions.Action0;
import rx.functions.Action1;
import timber.log.Timber;

public abstract class SubscribingPresenter implements HasActivityHooks {

    private SubscriptionManager<Activity> subscriptionManager;

    protected ActivityHookBuilder getSubscriptionManagingHookBuilder() {
        return new ActivityHookBuilder()
                .onCreate(this::onCreate)
                .onDestroy(this::onDestroy);
    }

    protected void onCreate(AppCompatActivity activity, Bundle savedInstanceState) {
        subscriptionManager = new ActivitySubscriptionManager(activity);
    }

    protected void onDestroy(AppCompatActivity activity) {
        Timber.d("unsubscribing from activity: " + activity.getClass().getSimpleName());
        subscriptionManager.unsubscribeAll();
    }

    protected  <O> void subscribe(final Observable<O> source, final Action1<O> onNext) {
        subscribe(source, onNext, null);
    }

    protected <O> void subscribe(final Observable<O> source, final Action1<O> onNext, final Action1<Throwable> onError) {
        subscribe(source, onNext, onError, null);
    }

    protected <O> void subscribe(final Observable<O> source, final Action1<O> onNext, final Action1<Throwable> onError, final Action0 onCompleted) {
        subscriptionManager.subscribe(source, new Observer<O>() {
            @Override
            public void onCompleted() {
                if (onCompleted != null) {
                    onCompleted.call();
                }
            }

            @Override
            public void onError(Throwable e) {
                if (onError != null) {
                    onError.call(e);
                }
            }

            @Override
            public void onNext(O o) {
                if (onNext != null) {
                    onNext.call(o);
                }
            }
        });
    }

    @Override
    public abstract Collection<ActivityHook> getActivityHooks();
}
