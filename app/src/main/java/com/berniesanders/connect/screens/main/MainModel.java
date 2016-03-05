package com.berniesanders.connect.screens.main;

import com.berniesanders.connect.application.ApplicationPreferences;
import com.berniesanders.connect.dagger.ActivityScope;

import javax.inject.Inject;

@ActivityScope
public class MainModel {
    private final ApplicationPreferences mApplicationPreferences;

    @Inject
    public MainModel(final ApplicationPreferences applicationPreferences) {
        mApplicationPreferences = applicationPreferences;
    }

    public boolean hasNotAgreedToTerms() {
        return !mApplicationPreferences.hasAgreedToTerms();
    }

    public void agreeToTerms() {
        mApplicationPreferences.agreeToTerms();
    }

    public boolean hasNotAgreedToPrivacy() {
        return !mApplicationPreferences.hasAgreedToPrivacy();
    }

    public void agreeToPrivacy() {
        mApplicationPreferences.agreeToPrivacy();
    }
}
