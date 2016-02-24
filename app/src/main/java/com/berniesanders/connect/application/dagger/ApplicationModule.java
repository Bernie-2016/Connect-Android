package com.berniesanders.connect.application.dagger;

import android.content.Context;

import com.berniesanders.connect.application.ConnectApplication;
import com.berniesanders.connect.api.ConnectApi;
import com.berniesanders.connect.gson.GsonFactory;
import com.berniesanders.connect.model.ActionAlertsModel;
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
    public ActionAlertsModel provideActionAlertsModel(final ConnectApi connectApi) {
        return new ActionAlertsModel(connectApi);
    }
}
