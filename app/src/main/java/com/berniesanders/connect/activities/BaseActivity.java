package com.berniesanders.connect.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.berniesanders.connect.hook.ActivityHook;
import com.berniesanders.connect.hook.ResumePauseLogger;
import com.berniesanders.connect.hook.SubscriptionManagerHook;
import com.berniesanders.connect.rx.ActivitySubscriptionManager;
import com.berniesanders.connect.rx.SubscriptionManager;

import java.util.LinkedList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.functions.Action0;
import rx.functions.Action1;

public abstract class BaseActivity extends AppCompatActivity {

    private final SubscriptionManager<Activity> subscriptionManager = new ActivitySubscriptionManager(this);

    private Iterable<ActivityHook> mHooks;

    protected Iterable<ActivityHook> getHooks() {
        List<ActivityHook> hooks = new LinkedList<>();
        hooks.add(new SubscriptionManagerHook(subscriptionManager));
        hooks.add(ResumePauseLogger.createHook());
        return hooks;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        mHooks = getHooks();
        super.onCreate(savedInstanceState);

        for (final ActivityHook hook : mHooks) {
            hook.onCreate(this, savedInstanceState);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        for (final ActivityHook hook : mHooks) {
            hook.onDestroy(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        for (final ActivityHook hook : mHooks) {
            hook.onResume(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        for (final ActivityHook hook : mHooks) {
            hook.onPause(this);
        }
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);

        for (final ActivityHook hook : mHooks) {
            hook.onSaveInstanceState(this, outState);
        }
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
}
