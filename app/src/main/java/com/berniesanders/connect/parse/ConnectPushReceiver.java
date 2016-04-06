package com.berniesanders.connect.parse;

import android.content.Context;
import android.content.Intent;

import com.berniesanders.connect.application.ApplicationPreferences;
import com.berniesanders.connect.application.ConnectApplication;
import com.parse.ParsePushBroadcastReceiver;

public class ConnectPushReceiver extends ParsePushBroadcastReceiver {
    @Override
    public void onReceive(final Context context, final Intent intent) {
        final ApplicationPreferences preferences = ((ConnectApplication) context.getApplicationContext()).getObjectGraph().getApplicationPreferences();

        if (preferences.isPushEnabled()) {
            super.onReceive(context, intent);
        }
    }
}
