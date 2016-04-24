package com.berniesanders.connect.db;

import android.content.Context;
import android.content.SharedPreferences;

import com.annimon.stream.Optional;
import com.berniesanders.connect.application.PrefName;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

@Singleton
public class GsonDb {
    public static final String ACTION_ALERTS = "ACTION_ALERTS";
    public static final String NEWS_ARTICLES = "NEWS_ARTICLES";

    private final SharedPreferences mPreferences;
    private final Gson mGson;
    private final Executor mExecutor;
    private final Scheduler mScheduler;

    @Inject
    public GsonDb(final Context context) {
        this(context.getSharedPreferences(PrefName.GSON_DB, Context.MODE_PRIVATE), new Gson(), Executors.newSingleThreadExecutor());
    }

    public GsonDb(final SharedPreferences preferences, final Gson gson, final Executor executor) {
        this(preferences, gson, executor, Schedulers.from(executor));
    }

    public GsonDb(final SharedPreferences preferences, final Gson gson, final Executor executor, final Scheduler scheduler) {
        mPreferences = preferences;
        mGson = gson;
        mExecutor = executor;
        mScheduler = scheduler;
    }

    public <T> Observable<Optional<T>> read(final String key, final TypeToken<T> typeToken) {
        return Observable
                .<Optional<T>>create(subscriber -> {
                    subscriber.onNext(Optional
                            .ofNullable(mPreferences.getString(key, null))
                            .map(json -> mGson.<T>fromJson(json, typeToken.getType())));

                    subscriber.onCompleted();
                })
                .subscribeOn(mScheduler);
    }

    public <T> void write(final String key, final T value) {
        mExecutor.execute(() -> mPreferences.edit().putString(key, mGson.toJson(value)).apply());
    }
}
