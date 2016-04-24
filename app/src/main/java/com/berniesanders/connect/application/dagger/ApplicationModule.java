package com.berniesanders.connect.application.dagger;

import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.berniesanders.connect.api.BaseUrls;
import com.berniesanders.connect.api.NewsFeedApi;
import com.berniesanders.connect.application.ApplicationPreferences;
import com.berniesanders.connect.application.ConnectApplication;
import com.berniesanders.connect.api.ConnectApi;
import com.berniesanders.connect.application.PrefName;
import com.berniesanders.connect.dagger.Name;
import com.berniesanders.connect.gson.GsonFactory;
import com.berniesanders.connect.model.ActionAlertsManager;
import com.berniesanders.connect.db.GsonDb;
import com.google.gson.Gson;

import javax.inject.Named;
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
    @Named(Name.DEFAULT)
    public Gson provideDefaultGson() {
        return GsonFactory.createDefault();
    }

    @Provides
    @Singleton
    @Named(Name.JSON_API)
    public Gson provideJsonApiGson() {
        return GsonFactory.createJsonApi();
    }

    @Provides
    @Singleton
    @Named(Name.CONNECT)
    public Retrofit provideConnectRetrofit(@Named(Name.JSON_API) final Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(BaseUrls.CONNECT_API)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    @Named(Name.SHARKNADO)
    public Retrofit provideSharknadoRetrofit(@Named(Name.DEFAULT) final Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(BaseUrls.SHARKNADO)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public ConnectApi provideConnectApi(@Named(Name.CONNECT) final Retrofit retrofit) {
        return retrofit.create(ConnectApi.class);
    }

    @Provides
    @Singleton
    public NewsFeedApi provideNewsFeedApi(@Named(Name.SHARKNADO) final Retrofit retrofit) {
        return retrofit.create(NewsFeedApi.class);
    }

    @Provides
    @Singleton
    public ActionAlertsManager provideActionAlertsModel(final ConnectApi connectApi, final GsonDb gsonDb) {
        return new ActionAlertsManager(connectApi, gsonDb);
    }
}
