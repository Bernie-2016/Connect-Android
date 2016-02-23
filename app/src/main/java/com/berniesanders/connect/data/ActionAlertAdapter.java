package com.berniesanders.connect.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.berniesanders.connect.R;
import com.berniesanders.connect.view.ActionAlertViewHolder;

import java.util.List;

/**
 * A recycler view adapter for Action Alerts
 *
 * Created by John on 2/21/16.
 */
public class ActionAlertAdapter extends RecyclerView.Adapter<ActionAlertViewHolder> {
    
    private List<ActionAlert> alerts;
    private final Callback callback;
    private final LayoutInflater inflater;

    public ActionAlertAdapter(Context context, Callback callback) {
        this(context, null, callback);
    }

    public ActionAlertAdapter(Context context, List<ActionAlert> alerts, Callback callback) {
        this.alerts = alerts;
        this.callback = callback;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        if (alerts == null) {
            return 0;
        }
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
        view.setOnClickListener(v -> callback.itemSelected(position, alert));
    }

    @Override
    public ActionAlertViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.alert_item, viewGroup, false);

        return new ActionAlertViewHolder(itemView);
    }

    public void setAlerts(@NonNull List<ActionAlert> alerts) {
        this.alerts = alerts;
        notifyDataSetChanged();
    }

    public interface Callback {
        void itemSelected(int position, ActionAlert alert);
    }

}
