package com.berniesanders.connect.screens.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.berniesanders.connect.R;
import com.berniesanders.connect.controller.DrawerView;
import com.berniesanders.connect.dagger.ActivityScope;
import com.berniesanders.connect.dialog.PrivacyPolicyDialog;
import com.berniesanders.connect.hook.ActivityHook;
import com.berniesanders.connect.hook.ActivityHookBuilder;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;

@ActivityScope
public class MainView {
    private final PrivacyPolicyDialog mPrivacyPolicyDialog;

    private DrawerView mDrawerView;

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Bind(R.id.navigation_view)
    NavigationView mNavigationView;

    @Inject
    public MainView(final PrivacyPolicyDialog privacyPolicyDialog) {
        mPrivacyPolicyDialog = privacyPolicyDialog;
    }

    public ActivityHook getActivityHook() {
        return new ActivityHookBuilder()
                .onCreate(this::onCreate)
                .build();
    }

    private void onCreate(final AppCompatActivity activity, final Bundle savedInstanceState) {
        activity.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.setContentView(R.layout.activity_main);
        ButterKnife.bind(this, activity);

        mNavigationView.inflateHeaderView(R.layout.drawer_header);
        mNavigationView.inflateMenu(R.menu.menu_main);
        mDrawerView = new DrawerView(mDrawerLayout, mNavigationView);
    }

    public DrawerView getDrawerView() {
        return mDrawerView;
    }

    public void showPrivacy(final boolean agreed) {
        mPrivacyPolicyDialog.show(agreed);
    }

    public Observable<Boolean> onAgreeToPrivacy() {
        return mPrivacyPolicyDialog.onAgreeToPrivacy();
    }
}
