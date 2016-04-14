package com.berniesanders.connect.parse;

import android.content.Context;

import com.berniesanders.connect.BuildConfig;
import com.parse.Parse;
import com.parse.ParseInstallation;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ParseManager {
    private final Context mContext;

    @Inject
    public ParseManager(final Context context) {
        mContext = context;
    }

    public void init() {
        if (BuildConfig.DEBUG) {
            Parse.setLogLevel(Parse.LOG_LEVEL_VERBOSE);
        }

        Parse.initialize(mContext, BuildConfig.PARSE_APP_ID, BuildConfig.PARSE_CLIENT_KEY);

        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
