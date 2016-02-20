package com.berniesanders.connect.dagger;

import android.content.Context;

import com.berniesanders.connect.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(final MainActivity activity);

    Context getContext();
}
