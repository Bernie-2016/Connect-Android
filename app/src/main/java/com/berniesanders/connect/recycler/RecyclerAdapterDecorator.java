package com.berniesanders.connect.recycler;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;

import com.annimon.stream.Stream;

import rx.Observable;

public class RecyclerAdapterDecorator extends DecorableAdapter {
    private final DecorableAdapter mAdapter;
    private final RecyclerAdapterDecoration mDecoration;

    public static DecorableAdapter decorate(final DecorableAdapter adapter, final RecyclerAdapterDecoration ... decorations) {
        final DecorableAdapter result;

        if (decorations == null) {
            result = adapter;
        } else {
            result = Stream.of(decorations).reduce(adapter, RecyclerAdapterDecorator::new);
        }

        result.getDataChanges().subscribe(next -> result.notifyDataSetChanged());
        return result;
    }

    public RecyclerAdapterDecorator(final DecorableAdapter adapter, final RecyclerAdapterDecoration decoration) {
        mAdapter = adapter;
        mDecoration = decoration;

        Observable.merge(adapter.getDataChanges(), decoration.getDataChanges()).subscribe(mDataChanges);
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        int adapterMaxViewType = mAdapter.getMaxViewType();

        if (viewType <= adapterMaxViewType) {
            return mAdapter.onCreateViewHolder(parent, viewType);
        } else {
            return mDecoration.onCreateViewHolder(parent, toDecorationViewType(viewType));
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (mDecoration.isAdapterPosition(mAdapter, position)) {
            mAdapter.onBindViewHolder(holder, mDecoration.getAdapterPosition(mAdapter, position));
        } else {
            mDecoration.onBindViewHolder(holder, position);
        }
    }

    @Override
    public int getItemViewType(final int position) {
        if (mDecoration.isAdapterPosition(mAdapter, position)) {
            return mAdapter.getItemViewType(mDecoration.getAdapterPosition(mAdapter, position));
        } else {
            return toAdapterViewType(mDecoration.getItemViewType(mAdapter, position));
        }
    }

    @Override
    public int getItemCount() {
        return mAdapter.getItemCount() + mDecoration.getItemCount(mAdapter);
    }

    @Override
    public int getMaxViewType() {
        return mAdapter.getMaxViewType() + mDecoration.getMaxViewType() + 1;
    }

    private int toDecorationViewType(final int adapterViewType) {
        return adapterViewType - mAdapter.getMaxViewType() - 1;
    }

    private int toAdapterViewType(final int decorationViewType) {
        return decorationViewType + mAdapter.getMaxViewType() + 1;
    }
}
