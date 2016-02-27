package com.berniesanders.connect.screens.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.berniesanders.connect.R;
import com.berniesanders.connect.controller.DrawerController;
import com.berniesanders.connect.dagger.ActivityScope;
import com.berniesanders.connect.data.ActionAlert;
import com.berniesanders.connect.data.ActionAlertAdapter;
import com.berniesanders.connect.dialog.AgreeDialog;
import com.berniesanders.connect.hook.ActivityHook;
import com.berniesanders.connect.hook.ActivityHookBuilder;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;

@ActivityScope
public class MainView {
    private final AgreeDialog mAgreeDialog;

    private ActionAlertAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DrawerController mDrawerController;

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Bind(R.id.navigation_view)
    NavigationView mNavigationView;

    @Inject
    public MainView(final AgreeDialog agreeDialog) {
        mAgreeDialog = agreeDialog;
    }

    public ActivityHook getActivityHook() {
        return new ActivityHookBuilder()
                .onCreate(this::onCreate)
                .build();
    }

    private void onCreate(final AppCompatActivity activity, final Bundle savedInstanceState) {
        activity.setContentView(R.layout.activity_main);
        ButterKnife.bind(this, activity);

        mAdapter = new ActionAlertAdapter(activity);
        mLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mNavigationView.inflateHeaderView(R.layout.drawer_header);
        mNavigationView.inflateMenu(R.menu.menu_main);
        mDrawerController = new DrawerController(mDrawerLayout, mNavigationView);
    }

    public DrawerController getDrawerController() {
        return mDrawerController;
    }

    public void setActionAlerts(final List<ActionAlert> actionAlerts) {
        mAdapter.setActionAlerts(actionAlerts);
    }

    public Observable<ActionAlert> getSelectedActionAlerts() {
        return mAdapter.getSelectedItems();
    }

    public void showTerms(final boolean agreed) {
        mAgreeDialog.showTerms(agreed);
    }

    public void showPrivacy(final boolean agreed) {
        mAgreeDialog.showPrivacy(agreed);
    }

    public Observable<Boolean> onAgreeToTerms() {
        return mAgreeDialog.onAgreeToTerms();
    }

    public Observable<Boolean> onAgreeToPrivacy() {
        return mAgreeDialog.onAgreeToPrivacy();
    }
}
