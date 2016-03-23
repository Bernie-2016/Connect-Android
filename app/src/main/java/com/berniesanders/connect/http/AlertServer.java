package com.berniesanders.connect.http;

import com.berniesanders.connect.dagger.ActivityScope;
import com.berniesanders.connect.data.ActionAlert;
import com.berniesanders.connect.model.ActionAlertsManager;

import javax.inject.Inject;

import fi.iki.elonen.NanoHTTPD;

@ActivityScope
public class AlertServer extends NanoHTTPD {
    public static final String URL = "http://localhost:8888/";

    private final ActionAlertsManager mAlertsManager;

    @Inject
    public AlertServer(final ActionAlertsManager alertsManager) {
        super(8888);

        mAlertsManager = alertsManager;
    }

    @Override
    public Response serve(final IHTTPSession session) {
        final String alertId = session.getUri().substring(1);

        return newFixedLengthResponse(mAlertsManager.getActionAlertById(alertId)
                .map(ActionAlert::bodyHtml)
                .orElse(""));
    }
}
