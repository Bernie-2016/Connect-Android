package com.berniesanders.connect.dagger;

import com.berniesanders.connect.activities.MainActivity;
import com.berniesanders.connect.application.dagger.ApplicationComponent;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(final MainActivity activity);
}
