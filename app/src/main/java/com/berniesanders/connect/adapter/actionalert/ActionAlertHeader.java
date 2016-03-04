package com.berniesanders.connect.adapter.actionalert;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.berniesanders.connect.R;
import com.berniesanders.connect.recycler.HeaderDecoration;

public class ActionAlertHeader extends HeaderDecoration {
    private final Context mContext;

    private int mNumAlerts;

    public ActionAlertHeader(final Context context) {
        mContext = context;
    }

    public void setNumAlerts(final int numAlerts) {
        mNumAlerts = numAlerts;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_header_view, parent, false);

        return new ViewHolder(view) {};
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final String string;

        if (mNumAlerts == 1) {
            string = mContext.getString(R.string.live_action_alert);
        } else {
            string = mContext.getString(R.string.live_action_alerts, mNumAlerts);
        }

        ((TextView) holder.itemView.findViewById(R.id.text)).setText(string);
    }

    @Override
    public int getItemCount(final Adapter adapter) {
        return adapter.getItemCount() == 0 ? 0 : 1;
    }
}
