package com.berniesanders.connect.screens.alert;

import android.view.View;

import com.berniesanders.connect.activities.BaseActivity;
import com.berniesanders.connect.dagger.ActivityScope;
import com.berniesanders.connect.dagger.AlertsComponent;
import com.berniesanders.connect.dagger.AlertsModule;
import com.berniesanders.connect.dagger.DaggerAlertsComponent;
import com.berniesanders.connect.screen.Screen;
import com.berniesanders.connect.screen.ScreenComponent;
import com.berniesanders.connect.screen.ScreenView;

import java.util.Arrays;
import java.util.Collection;

import javax.inject.Inject;

@ActivityScope
public class AlertsScreen implements Screen<View> {
    private final BaseActivity mActivity;

    private AlertsComponent mComponent;
    private AlertsView mView;
    private AlertsPresenter mPresenter;

    @Inject
    public AlertsScreen(final BaseActivity activity) {
        mActivity = activity;
    }

    @Override
    public void create() {
        mComponent = DaggerAlertsComponent.builder()
                .activityComponent(mActivity.getObjectGraph())
                .alertsModule(new AlertsModule())
                .build();

        mView = mComponent.getAlertsView();
        mPresenter = mComponent.getAlertsPresenter();
    }

    @Override
    public void destroy() {
        mComponent = null;
        mView = null;
        mPresenter = null;
    }

    @Override
    public ScreenView<View> getView() {
        return mView;
    }

    @Override
    public Collection<ScreenComponent> getComponents() {
        return Arrays.asList(mView, mPresenter);
    }
}
