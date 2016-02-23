package com.berniesanders.connect.data;

import android.app.Notification;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.berniesanders.connect.R;
import com.berniesanders.connect.view.ActionAlertViewHolder;

import java.util.List;

import javax.inject.Inject;

/**
 * A simple array adapter for Action Alerts
 *
 * Created by John on 2/21/16.
 */
public class ActionAlertAdapter extends RecyclerView.Adapter<ActionAlertViewHolder> {
    
    private final List<ActionAlert> alerts;
    private final Callback callback;

    public ActionAlertAdapter(List<ActionAlert> alerts, Callback callback) {
        this.alerts = alerts;
        this.callback = callback;
    }

    @Override
    public int getItemCount() {
        return alerts.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(ActionAlertViewHolder alertViewHolder, int position) {
        ActionAlert alert = alerts.get(position);
        alertViewHolder.alertTitleTV.setText(alert.title());

        // Set on click listener
        View view = alertViewHolder.itemView;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), "View clicked at " + position, Toast.LENGTH_LONG).show();
                callback.itemSelected(position, alert);
            }
        });
    }

    @Override
    public ActionAlertViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.alert_item, viewGroup, false);

        return new ActionAlertViewHolder(itemView);
    }

    public interface Callback {
        public void itemSelected(int position, ActionAlert alert);
    }

}
