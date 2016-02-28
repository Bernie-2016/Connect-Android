package com.berniesanders.connect.adapter.actionalert;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.berniesanders.connect.R;
import com.berniesanders.connect.data.ActionAlert;
import com.berniesanders.connect.recycler.DecorableAdapter;

import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * A recycler view adapter for Action Alerts
 *
 * Created by John on 2/21/16.
 */
public class ActionAlertAdapter extends DecorableAdapter<ActionAlertViewHolder> {
    private final LayoutInflater inflater;
    private final PublishSubject<ActionAlert> mSelectedSubject = PublishSubject.create();
    private final PublishSubject<Void> mChangedSubject = PublishSubject.create();

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

        alertViewHolder.textPrimary.setText(alert.title());
        alertViewHolder.itemView.setOnClickListener(view -> mSelectedSubject.onNext(alert));
    }

    @Override
    public ActionAlertViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        final View itemView = inflater.inflate(R.layout.alert_item, viewGroup, false);

        return ActionAlertViewHolder.createActionAlert(itemView);
    }

    @Override
    public int getMaxViewType() {
        return 0;
    }

    @Override
    public Observable<Void> onChange() {
        return mChangedSubject;
    }

    public Observable<ActionAlert> getSelectedItems() {
        return mSelectedSubject;
    }

    public void setActionAlerts(@NonNull List<ActionAlert> alerts) {
        if (!alerts.equals(mAlerts)) {
            mAlerts = alerts;
        }

        notifyDataSetChanged();
        mChangedSubject.onNext(null);
    }
}
