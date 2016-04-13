package com.berniesanders.connect.rx;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import timber.log.Timber;

public class RxError {
    public static Action1<Throwable> logError() {
        return error -> Timber.e(error, "");
    }

    public static <T> Func1<Throwable, Observable<T>> logNever() {
        return logNever("");
    }

    public static <T> Func1<Throwable, Observable<T>> logNever(final String message) {
        return error -> {
            Timber.e(error, message);
            return Observable.never();
        };
    }
}
