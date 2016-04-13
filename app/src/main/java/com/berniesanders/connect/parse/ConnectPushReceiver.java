package com.berniesanders.connect.parse;

import android.content.Context;
import android.content.Intent;

import com.annimon.stream.Optional;
import com.berniesanders.connect.application.ApplicationPreferences;
import com.berniesanders.connect.application.ConnectApplication;
import com.berniesanders.connect.application.dagger.ApplicationComponent;
import com.berniesanders.connect.data.PushAlert;
import com.berniesanders.connect.data.PushGson;
import com.berniesanders.connect.model.ActionAlertsManager;
import com.berniesanders.connect.notification.NotificationController;
import com.berniesanders.connect.rx.RxError;
import com.berniesanders.connect.util.OptionalUtil;
import com.google.gson.Gson;
import com.parse.ParsePushBroadcastReceiver;

import java.util.HashSet;
import java.util.Set;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

import static com.annimon.stream.Optional.ofNullable;
import static com.berniesanders.connect.util.OptionalUtil.notEmpty;

public class ConnectPushReceiver extends ParsePushBroadcastReceiver {
    private static final String KEY_JSON_PAYLOAD = "com.parse.Data";

    private static final Set<String> sIdentifiers = new HashSet<>();

    @Override
    public void onReceive(final Context context, final Intent intent) {
        final ApplicationComponent applicationComponent = ((ConnectApplication) context.getApplicationContext()).getObjectGraph();
        final ApplicationPreferences preferences = applicationComponent.getApplicationPreferences();
        final ActionAlertsManager actionAlertsManager = applicationComponent.getActionAlertsManager();
        final NotificationController notificationController = applicationComponent.getNotificationController();

        if (preferences.isPushEnabled()) {
            final Optional<PushAlert> optionalPushAlert = notEmpty(intent.getStringExtra(KEY_JSON_PAYLOAD))
                    .flatMap(json -> ofNullable(new Gson().fromJson(json, PushGson.class)))
                    .map(PushGson::toValue);

            if (optionalPushAlert.isPresent()) {
                final PushAlert pushAlert = optionalPushAlert.get();

                OptionalUtil.notEmpty(pushAlert.identifier())
                        .filter(sIdentifiers::add)
                        .map(identifier -> actionAlertsManager.requestActionAlerts())
                        .orElse(Observable.empty())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(next -> notificationController.notifyActionAlert(pushAlert), RxError.logError());
            } else {
                super.onReceive(context, intent);
            }
        }
    }
}
