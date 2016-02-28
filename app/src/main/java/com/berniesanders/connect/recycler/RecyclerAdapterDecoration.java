package com.berniesanders.connect.recycler;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;

public interface RecyclerAdapterDecoration<VH extends ViewHolder> extends HasMaxViewType, DataSetChangeable {
    boolean isAdapterPosition(final Adapter<VH> adapter, final int position);

    int getAdapterPosition(final Adapter<VH> adapter, final int position);

    VH onCreateViewHolder(final ViewGroup parent, final int viewType);

    void onBindViewHolder(final Adapter<VH> adapter, final VH holder, final int position);

    int getItemViewType(final Adapter<VH> adapter, final int position);

    int getItemCount(final Adapter<VH> adapter);
}
