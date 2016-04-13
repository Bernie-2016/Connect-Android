package com.berniesanders.connect.application.dagger;

import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.berniesanders.connect.application.ApplicationPreferences;
import com.berniesanders.connect.application.ConnectApplication;
import com.berniesanders.connect.api.ConnectApi;
import com.berniesanders.connect.application.PrefName;
import com.berniesanders.connect.gson.GsonFactory;
import com.berniesanders.connect.model.ActionAlertsManager;
import com.berniesanders.connect.util.GsonDb;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

@Module
public class ApplicationModule {
    private final ConnectApplication mApplication;

    public ApplicationModule(final ConnectApplication application) {
        mApplication = application;
    }

    @Provides
    public Context provideContext() {
        return mApplication;
    }

    @Provides
    public NotificationManager provideNotificationManager(final Context context) {
        return (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Provides
    public Resources provideResources(final Context context) {
        return context.getResources();
    }

    @Provides
    public DisplayMetrics provideDisplayMetrics(final Resources resources) {
        return resources.getDisplayMetrics();
    }

    @Provides
    @Singleton
    public ApplicationPreferences provideApplicationPreferences(final Context context) {
        return new ApplicationPreferences(context.getSharedPreferences(PrefName.APPLICATION, Context.MODE_PRIVATE));
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        return GsonFactory.create();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(final Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(ConnectApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public ConnectApi provideConnectApi(final Retrofit retrofit) {
        return retrofit.create(ConnectApi.class);
    }

    @Provides
    @Singleton
    public ActionAlertsManager provideActionAlertsModel(final ConnectApi connectApi, final GsonDb gsonDb) {
        return new ActionAlertsManager(connectApi, gsonDb);
    }
}
