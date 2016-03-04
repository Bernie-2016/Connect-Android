package com.berniesanders.connect.recycler;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;

import rx.Observable;
import rx.subjects.PublishSubject;

public abstract class DecorableAdapter extends Adapter<ViewHolder> implements HasMaxViewType, CanChangeData {
    protected final PublishSubject<Void> mDataChanges = PublishSubject.create();

    protected void onDataChanged() {
        mDataChanges.onNext(null);
    }

    @Override
    public Observable<Void> getDataChanges() {
        return mDataChanges;
    }
}
