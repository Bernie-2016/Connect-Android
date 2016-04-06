package com.berniesanders.connect.screens.settings;

import com.berniesanders.connect.dagger.ActivityScope;
import com.berniesanders.connect.hook.ActivityHook;

import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

@ActivityScope
public class SettingsPresenter {
    private final SettingsView mView;

    @Inject
    public SettingsPresenter(final SettingsView view) {
        mView = view;
    }

    public Collection<ActivityHook> getActivityHooks() {
        return Collections.singletonList(mView.getActivityHook());
    }
}
