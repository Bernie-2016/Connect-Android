package com.berniesanders.connect.screens.main;

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

    @Inject
    public MainModel(final ActionAlertsModel actionAlertsModel) {
        mActionAlertsModel = actionAlertsModel;
    }

    public ActivityHook getActivityHook() {
        return new ActivityHookBuilder().build();
    }

    public Observable<List<ActionAlert>> getActionAlerts() {
        return mActionAlertsModel.getActionAlerts();
    }
}
