package com.berniesanders.connect.application;

import android.content.SharedPreferences;

public class ApplicationPreferences {
    public static final String KEY_AGREED_TO_PRIVACY = "agreed-to-private";
    public static final String KEY_PUSH_MASTER = "push-master";

    private final SharedPreferences mPreferences;

    public ApplicationPreferences(final SharedPreferences preferences) {
        mPreferences = preferences;
    }

    public boolean hasAgreedToPrivacy() {
        return mPreferences.getBoolean(KEY_AGREED_TO_PRIVACY, false);
    }

    public void agreeToPrivacy() {
        mPreferences.edit().putBoolean(KEY_AGREED_TO_PRIVACY, true).apply();
    }

    public boolean isPushEnabled() {
        return mPreferences.getBoolean(KEY_PUSH_MASTER, true);
    }

    public void setPushEnabled(final boolean enabled) {
        mPreferences.edit().putBoolean(KEY_PUSH_MASTER, enabled).apply();
    }
}
