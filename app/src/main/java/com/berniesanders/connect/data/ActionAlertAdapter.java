package com.berniesanders.connect.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.berniesanders.connect.R;
import com.berniesanders.connect.view.ActionAlertViewHolder;

import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * A recycler view adapter for Action Alerts
 *
 * Created by John on 2/21/16.
 */
public class ActionAlertAdapter extends RecyclerView.Adapter<ActionAlertViewHolder> {
    private final LayoutInflater inflater;
    private final PublishSubject<ActionAlert> mSubject = PublishSubject.create();

    private List<ActionAlert> mAlerts = Collections.emptyList();

    public ActionAlertAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return mAlerts.size();
    }

    @Override
    public long getItemId(int position) {
        return RecyclerView.NO_ID;
    }

    @Override
    public void onBindViewHolder(ActionAlertViewHolder alertViewHolder, int position) {
        final ActionAlert alert = mAlerts.get(position);

        alertViewHolder.alertTitleTV.setText(alert.title());
        alertViewHolder.itemView.setOnClickListener(view -> mSubject.onNext(alert));
    }

    @Override
    public ActionAlertViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = inflater.inflate(R.layout.alert_item, viewGroup, false);

        return new ActionAlertViewHolder(itemView);
    }

    public Observable<ActionAlert> getSelectedItems() {
        return mSubject;
    }

    public void setActionAlerts(@NonNull List<ActionAlert> alerts) {
        if (!alerts.equals(mAlerts)) {
            mAlerts = alerts;
        }

        notifyDataSetChanged();
    }
}
