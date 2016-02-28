package com.berniesanders.connect.recycler;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;

public interface RecyclerAdapterDecoration<VH extends ViewHolder> extends HasMaxViewType, DataSetChangeable {
    /**
     * @param position position in adapter + decoration
     * @return true if the position belongs to the adapter, false if it belongs to the decoration
     */
    boolean isAdapterPosition(final Adapter<VH> adapter, final int position);

    /**
     * @param position position in adapter + decoration
     * @return position in adapter
     */
    int getAdapterPosition(final Adapter<VH> adapter, final int position);

    /**
     * @param viewType view type in the decoration
     * @return view holder for the decoration
     * @see {@link Adapter#onCreateViewHolder(ViewGroup, int)}
     */
    VH onCreateViewHolder(final ViewGroup parent, final int viewType);

    /**
     * Bind the view holder for the decoration.
     * @param position position in adapter + decoration
     * @see {@link Adapter#onBindViewHolder(VH, int)}
     */
    void onBindViewHolder(final Adapter<VH> adapter, final VH holder, final int position);

    /**
     * @param position position in adapter + decoration
     * @return view type in the decoration
     * @see {@link Adapter#getItemViewType(int)}
     */
    int getItemViewType(final Adapter<VH> adapter, final int position);

    /**
     * @return number of items in the decoration
     * @see {@link Adapter#getItemCount()}
     */
    int getItemCount(final Adapter<VH> adapter);
}
