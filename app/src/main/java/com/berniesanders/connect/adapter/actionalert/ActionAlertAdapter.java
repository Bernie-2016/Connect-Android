package com.berniesanders.connect.adapter.actionalert;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;

import com.berniesanders.connect.data.ActionAlert;
import com.berniesanders.connect.recycler.DecorableAdapter;
import com.berniesanders.connect.view.ActionAlertView;

import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * A recycler view adapter for Action Alerts
 *
 * Created by John on 2/21/16.
 */
public class ActionAlertAdapter extends DecorableAdapter {
    private final PublishSubject<ActionAlert> mSelectedSubject = PublishSubject.create();
    private final PublishSubject<Void> mChangedSubject = PublishSubject.create();

    private List<ActionAlert> mAlerts = Collections.emptyList();

    public ActionAlertAdapter() {
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
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ActionAlertHolder(new ActionAlertView(viewGroup.getContext()));
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        final ActionAlert actionAlert = mAlerts.get(position);
        final ActionAlertHolder alertHolder = (ActionAlertHolder) viewHolder;

        alertHolder.setActionAlert(actionAlert);
        alertHolder.setOnSelected(mSelectedSubject::onNext);
    }

    @Override
    public int getMaxViewType() {
        return 0;
    }

    @Override
    public Observable<Void> getDataChanges() {
        return mChangedSubject;
    }

    public Observable<ActionAlert> getSelectedItems() {
        return mSelectedSubject;
    }

    public void setActionAlerts(final List<ActionAlert> actionAlerts) {
        if (!actionAlerts.equals(mAlerts)) {
            mAlerts = actionAlerts;
        }

        notifyDataSetChanged();
        mChangedSubject.onNext(null);
    }
}
