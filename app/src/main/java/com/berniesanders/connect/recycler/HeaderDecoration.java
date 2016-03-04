package com.berniesanders.connect.recycler;

import android.support.v7.widget.RecyclerView.Adapter;

import rx.Observable;

public abstract class HeaderDecoration implements RecyclerAdapterDecoration {
    @Override
    public boolean isAdapterPosition(final Adapter adapter, final int position) {
        return position > 0;
    }

    @Override
    public int getAdapterPosition(final Adapter adapter, final int position) {
        return position - 1;
    }

    @Override
    public int getItemViewType(final Adapter adapter, final int position) {
        return 0;
    }

    @Override
    public int getItemCount(final Adapter adapter) {
        return 1;
    }

    @Override
    public int getMaxViewType() {
        return 0;
    }

    @Override
    public Observable<Void> getDataChanges() {
        return Observable.never();
    }
}
