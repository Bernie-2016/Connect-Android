package com.berniesanders.connect.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Style;
import android.support.v7.app.NotificationCompat;

import com.berniesanders.connect.R;
import com.berniesanders.connect.data.PushAlert;
import com.berniesanders.connect.screens.main.MainActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NotificationController {
    private static final int ID_ACTION_ALERT = 1;

    private final Context mContext;
    private final NotificationManager mNotificationManager;

    @Inject
    public NotificationController(final Context context, final NotificationManager notificationManager) {
        mContext = context;
        mNotificationManager = notificationManager;
    }

    public void notifyActionAlert(final PushAlert pushAlert) {
        final String title = "New Action Alert";
        final String message = pushAlert.alert();

        mNotificationManager.notify(ID_ACTION_ALERT, new NotificationCompat.Builder(mContext)
                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(openAppIntent())
                .setAutoCancel(true)
                .setStyle(makeBigText(message))
                .build());
    }

    private PendingIntent openAppIntent() {
        return PendingIntent.getActivity(mContext, 0, new Intent(mContext, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private Style makeBigText(final String bigText) {
        return new BigTextStyle().bigText(bigText);
    }
}
