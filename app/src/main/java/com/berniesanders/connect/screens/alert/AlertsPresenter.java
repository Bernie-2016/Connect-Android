package com.berniesanders.connect.screens.alert;

import android.app.Activity;

import com.berniesanders.connect.dagger.AlertsScope;
import com.berniesanders.connect.rx.ActivitySubscriptionManager;
import com.berniesanders.connect.rx.RxError;
import com.berniesanders.connect.screen.ScreenComponent;

import javax.inject.Inject;

@AlertsScope
public class AlertsPresenter implements ScreenComponent {
    private final AlertsModel mModel;
    private final IAlertsView mView;
    private final ActivitySubscriptionManager mSubscriptionManager;

    @Inject
    public AlertsPresenter(final Activity activity, final AlertsModel model, final IAlertsView view) {
        mModel = model;
        mView = view;
        mSubscriptionManager = new ActivitySubscriptionManager(activity);
    }

    @Override
    public void show() {
        mSubscriptionManager.subscribe(
                mModel.getActionAlerts().onErrorResumeNext(RxError.logNever("action alerts")),
                mView::setActionAlerts);
    }

    @Override
    public void hide() {
        mSubscriptionManager.unsubscribe();
    }
}
