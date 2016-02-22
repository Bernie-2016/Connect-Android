package com.berniesanders.connect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.FrameLayout;

import com.annimon.stream.Optional;

import com.berniesanders.connect.data.ActionAlert;
import com.berniesanders.connect.data.ActionAlertAdapter;
import com.berniesanders.connect.model.ActionAlertsModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements ActionAlertAdapter.Callback {
    @Inject
    ActionAlertsModel mActionAlertsModel;

    @Bind(value = R.id.root)
    FrameLayout mRoot;

    @Bind(R.id.alertListView)
    RecyclerView mAlertRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConnectApplication.component().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Configure RecyclerView layout
        // todo: get "No Adapter attached; skipping layout" RecyclerView error
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mAlertRecyclerView.setLayoutManager(layoutManager);

        mActionAlertsModel.getActionAlerts().subscribe(
                alerts -> mAlertRecyclerView.setAdapter(new ActionAlertAdapter(alerts, this)),
                error -> Timber.d("error: " + error),
                () -> {
                });
    }

    @Override
    public void itemSelected(int position, ActionAlert alert) {
        // Do things here, such as view the action alert detail
        Timber.d("Title: " + alert.title());

    }
}
