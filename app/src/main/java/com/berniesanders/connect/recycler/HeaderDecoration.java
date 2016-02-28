package com.berniesanders.connect.recycler;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;

import rx.Observable;

public abstract class HeaderDecoration<VH extends ViewHolder> implements RecyclerAdapterDecoration<VH> {
    @Override
    public boolean isAdapterPosition(final Adapter<VH> adapter, final int position) {
        return position > 0;
    }

    @Override
    public int getAdapterPosition(final Adapter<VH> adapter, final int position) {
        return position - 1;
    }

    @Override
    public int getItemViewType(final Adapter<VH> adapter, final int position) {
        return 0;
    }

    @Override
    public int getItemCount(final Adapter<VH> adapter) {
        return 1;
    }

    @Override
    public int getMaxViewType() {
        return 0;
    }

    @Override
    public Observable<Void> onChange() {
        return Observable.never();
    }
}
