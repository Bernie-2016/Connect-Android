package com.berniesanders.connect.screens.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.berniesanders.connect.R;
import com.berniesanders.connect.dagger.ActivityScope;
import com.berniesanders.connect.data.ActionAlert;
import com.berniesanders.connect.data.ActionAlertAdapter;
import com.berniesanders.connect.hook.ActivityHook;
import com.berniesanders.connect.hook.ActivityHookBuilder;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;

@ActivityScope
public class MainView {
    private ActionAlertAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Bind(R.id.navigation_view)
    NavigationView mNavigationView;

    @Inject
    public MainView() {
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
    }

    public void setActionAlerts(final List<ActionAlert> actionAlerts) {
        mAdapter.setActionAlerts(actionAlerts);
    }

    public Observable<ActionAlert> getSelectedActionAlerts() {
        return mAdapter.getSelectedItems();
    }
}
