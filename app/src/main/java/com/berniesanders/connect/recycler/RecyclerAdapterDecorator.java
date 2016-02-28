package com.berniesanders.connect.recycler;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import rx.Observable;

public class RecyclerAdapterDecorator<VH extends ViewHolder> extends DecorableAdapter<VH> {
    private final DecorableAdapter<VH> mAdapter;
    private final RecyclerAdapterDecoration<VH> mDecoration;

    @SafeVarargs
    public static <VH extends ViewHolder> DecorableAdapter<VH> decorate(final DecorableAdapter<VH> adapter, final RecyclerAdapterDecoration<VH>... decorations) {
        final DecorableAdapter<VH> result;
        Observable<Void> onChange = adapter.onChange();

        if (decorations == null || decorations.length == 0) {
            result = adapter;
        } else {
            result = Stream.of(decorations).reduce(adapter, RecyclerAdapterDecorator::new);

            onChange = Observable.merge(Stream.concat(
                    Stream.of(onChange),
                    Stream.of(decorations).map(DataSetChangeable::onChange))
                    .collect(Collectors.toList()));
        }

        onChange.subscribe(next -> result.notifyDataSetChanged());
        return result;
    }

    public RecyclerAdapterDecorator(final DecorableAdapter<VH> adapter, final RecyclerAdapterDecoration<VH> decoration) {
        mAdapter = adapter;
        mDecoration = decoration;
    }

    private void subscribeOnChange(final Observable<Void> onChange) {
        onChange.subscribe(next -> notifyDataSetChanged());
    }

    @Override
    public VH onCreateViewHolder(final ViewGroup parent, final int viewType) {
        int adapterMaxViewType = mAdapter.getMaxViewType();

        if (viewType <= adapterMaxViewType) {
            return mAdapter.onCreateViewHolder(parent, viewType);
        } else {
            return mDecoration.onCreateViewHolder(parent, toDecorationViewType(viewType));
        }
    }

    @Override
    public void onBindViewHolder(final VH holder, final int position) {
        if (mDecoration.isAdapterPosition(mAdapter, position)) {
            mAdapter.onBindViewHolder(holder, mDecoration.getAdapterPosition(mAdapter, position));
        } else {
            mDecoration.onBindViewHolder(mAdapter, holder, position);
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

    @Override
    public Observable<Void> onChange() {
        return mAdapter.onChange();
    }
}
