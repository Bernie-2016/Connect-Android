package com.berniesanders.connect.rx;

import android.app.Activity;

import com.berniesanders.connect.dagger.ActivityScope;
import com.berniesanders.connect.hook.ActivityHook;
import com.berniesanders.connect.hook.ActivityHookBuilder;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.functions.Action0;
import rx.functions.Action1;
import timber.log.Timber;

@ActivityScope
public class ActivitySubscriptionManager extends SubscriptionManager<Activity> {
    @Inject
    public ActivitySubscriptionManager(Activity activity) {
        super(activity);
    }

    @Override
    protected boolean validate(Activity activity) {
        return !activity.isFinishing();
    }

    public ActivityHook getActivityHook() {
        return new ActivityHookBuilder()
                .onDestroy(activity -> {
                    Timber.d("unsubscribing from activity: " + activity.getClass().getSimpleName());
                    unsubscribeAll();
                })
                .build();
    }

    public <O> void subscribe(final Observable<O> source, final Action1<O> onNext) {
        subscribe(source, onNext, null);
    }

    public <O> void subscribe(final Observable<O> source, final Action1<O> onNext, final Action1<Throwable> onError) {
        subscribe(source, onNext, onError, null);
    }

    public <O> void subscribe(final Observable<O> source, final Action1<O> onNext, final Action1<Throwable> onError, final Action0 onCompleted) {
        subscribe(source, new Observer<O>() {
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
