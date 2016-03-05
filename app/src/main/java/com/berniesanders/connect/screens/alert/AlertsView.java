package com.berniesanders.connect.screens.alert;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.berniesanders.connect.R;
import com.berniesanders.connect.adapter.actionalert.ActionAlertAdapter;
import com.berniesanders.connect.adapter.actionalert.ActionAlertHeader;
import com.berniesanders.connect.dagger.AlertsScope;
import com.berniesanders.connect.data.ActionAlert;
import com.berniesanders.connect.screen.ScreenView;
import com.berniesanders.connect.screen.ViewScreenFactory;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;

import static com.berniesanders.connect.recycler.RecyclerAdapterDecorator.decorate;

@AlertsScope
public class AlertsView implements ScreenView<View>, IAlertsView {
    private final Activity mActivity;
    private final ViewScreenFactory mViewFactory;

    private View mView;
    private ActionAlertAdapter mAdapter;
    private ActionAlertHeader mActionAlertHeader;

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Inject
    public AlertsView(final Activity activity, final ViewScreenFactory viewFactory) {
        mActivity = activity;
        mViewFactory = viewFactory;
    }

    @Override
    public View create() {
        mView = mViewFactory.inflate(R.layout.action_alerts_screen);

        ButterKnife.bind(this, mView);

        mAdapter = new ActionAlertAdapter();
        mActionAlertHeader = new ActionAlertHeader(mActivity);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(decorate(mAdapter, mActionAlertHeader));

        return mView;
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void setActionAlerts(final List<ActionAlert> actionAlerts) {
        mActionAlertHeader.setNumAlerts(actionAlerts.size());
        mAdapter.setActionAlerts(actionAlerts);
    }

    @Override
    public Observable<ActionAlert> getSelectedActionAlerts() {
        return mAdapter.getSelectedItems();
    }
}
