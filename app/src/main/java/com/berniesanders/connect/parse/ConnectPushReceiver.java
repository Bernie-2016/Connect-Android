package com.berniesanders.connect.parse;

import android.content.Context;
import android.content.Intent;

import com.berniesanders.connect.application.ApplicationPreferences;
import com.berniesanders.connect.application.ConnectApplication;
import com.berniesanders.connect.application.dagger.ApplicationComponent;
import com.berniesanders.connect.data.AlertPushGson;
import com.berniesanders.connect.model.ActionAlertsManager;
import com.google.gson.Gson;
import com.parse.ParsePushBroadcastReceiver;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

import static com.annimon.stream.Optional.ofNullable;
import static com.berniesanders.connect.util.OptionalUtil.notEmpty;

public class ConnectPushReceiver extends ParsePushBroadcastReceiver {
    private static final String KEY_JSON_PAYLOAD = "com.parse.Data";

    @Override
    public void onReceive(final Context context, final Intent intent) {
        final ApplicationComponent applicationComponent = ((ConnectApplication) context.getApplicationContext()).getObjectGraph();
        final ApplicationPreferences preferences = applicationComponent.getApplicationPreferences();
        final ActionAlertsManager actionAlertsManager = applicationComponent.getActionAlertsManager();

        if (preferences.isPushEnabled()) {
            notEmpty(intent.getStringExtra(KEY_JSON_PAYLOAD))
                    .flatMap(json -> ofNullable(new Gson().fromJson(json, AlertPushGson.class)))
                    .flatMap(alertPushGson -> alertPushGson.toValue().getIdentifier())
                    .map(identifier -> actionAlertsManager.requestActionAlerts())
                    .orElse(Observable.empty())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnTerminate(() -> super.onReceive(context, intent))
                    .subscribe(next -> {}, error -> {});
        }
    }
}
