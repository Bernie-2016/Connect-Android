package com.berniesanders.connect.dagger;

import com.berniesanders.connect.screens.main.MainActivity;
import com.berniesanders.connect.application.dagger.ApplicationComponent;
import com.berniesanders.connect.screens.detail.DetailActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(final MainActivity activity);
    void inject(final DetailActivity activity);
}
