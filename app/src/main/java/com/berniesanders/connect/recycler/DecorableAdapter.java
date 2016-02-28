package com.berniesanders.connect.recycler;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;

public abstract class DecorableAdapter<VH extends ViewHolder> extends Adapter<VH> implements HasMaxViewType, DataSetChangeable {
}
