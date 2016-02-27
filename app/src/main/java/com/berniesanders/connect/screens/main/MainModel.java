package com.berniesanders.connect.screens.main;

import com.berniesanders.connect.application.ApplicationPreferences;
import com.berniesanders.connect.dagger.ActivityScope;
import com.berniesanders.connect.data.ActionAlert;
import com.berniesanders.connect.hook.ActivityHook;
import com.berniesanders.connect.hook.ActivityHookBuilder;
import com.berniesanders.connect.model.ActionAlertsModel;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

@ActivityScope
public class MainModel {
    private final ActionAlertsModel mActionAlertsModel;
    private final ApplicationPreferences mApplicationPreferences;

    @Inject
    public MainModel(final ActionAlertsModel actionAlertsModel, final ApplicationPreferences applicationPreferences) {
        mActionAlertsModel = actionAlertsModel;
        mApplicationPreferences = applicationPreferences;
    }

    public ActivityHook getActivityHook() {
        return new ActivityHookBuilder().build();
    }

    public Observable<List<ActionAlert>> getActionAlerts() {
        return mActionAlertsModel.getActionAlerts();
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
