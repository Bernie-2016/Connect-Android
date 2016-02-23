package com.berniesanders.connect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.berniesanders.connect.model.ActionAlertsModel;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {
    @Inject
    ActionAlertsModel mActionAlertsModel;

    @Bind(value = R.id.root)
    FrameLayout mRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ConnectApplication)getApplication()).getObjectGraph().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mActionAlertsModel.getActionAlerts().subscribe(
                alerts -> Timber.d("alerts: " + alerts),
                error -> {},
                () -> {});
    }
}
