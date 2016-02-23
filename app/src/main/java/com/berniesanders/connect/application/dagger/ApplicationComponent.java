package com.berniesanders.connect.application.dagger;

import com.berniesanders.connect.activities.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(final MainActivity activity);
}
