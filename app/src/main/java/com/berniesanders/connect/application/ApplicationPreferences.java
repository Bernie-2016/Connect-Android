package com.berniesanders.connect.application;

import android.content.SharedPreferences;

public class ApplicationPreferences {
    private static final String KEY_AGREED_TO_TERMS = "agreed-to-terms";
    private static final String KEY_AGREED_TO_PRIVACY = "agreed-to-private";

    private final SharedPreferences mPreferences;

    public ApplicationPreferences(final SharedPreferences preferences) {
        mPreferences = preferences;
    }

    public boolean hasAgreedToTerms() {
        return mPreferences.getBoolean(KEY_AGREED_TO_TERMS, false);
    }

    public void agreeToTerms() {
        mPreferences.edit().putBoolean(KEY_AGREED_TO_TERMS, true).apply();
    }

    public boolean hasAgreedToPrivacy() {
        return mPreferences.getBoolean(KEY_AGREED_TO_PRIVACY, false);
    }

    public void agreeToPrivacy() {
        mPreferences.edit().putBoolean(KEY_AGREED_TO_PRIVACY, true).apply();
    }
}
