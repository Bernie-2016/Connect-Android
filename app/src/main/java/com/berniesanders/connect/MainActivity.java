package com.berniesanders.connect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.widget.FrameLayout;

import com.berniesanders.connect.data.ActionAlert;
import com.berniesanders.connect.data.ActionAlertAdapter;
import com.berniesanders.connect.model.ActionAlertsModel;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements ActionAlertAdapter.Callback {

    @Inject
    ActionAlertsModel actionAlertsModel;

    @Bind(value = R.id.root)
    FrameLayout root;

    @Bind(R.id.alertListView)
    RecyclerView alertsRecyclerView;

    private ActionAlertAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ConnectApplication)getApplication()).getObjectGraph().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Configure RecyclerView layout
        adapter = new ActionAlertAdapter(this, this);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        alertsRecyclerView.setLayoutManager(layoutManager);
        alertsRecyclerView.setAdapter(adapter);

        actionAlertsModel.getActionAlerts().subscribe(
                adapter::setAlerts,
                error -> Timber.d("error: " + error)
        );
    }

    @Override
    public void itemSelected(int position, ActionAlert alert) {
        // Do things here, such as view the action alert detail
        Timber.d("Title: " + alert.title());
    }
}
