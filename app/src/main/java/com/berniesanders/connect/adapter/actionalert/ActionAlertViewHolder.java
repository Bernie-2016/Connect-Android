package com.berniesanders.connect.adapter.actionalert;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.berniesanders.connect.R;

/**
 * Created by John on 2/23/16.
 */
public class ActionAlertViewHolder extends RecyclerView.ViewHolder {

    public final TextView textPrimary;

    public static ActionAlertViewHolder createActionAlert(final View view) {
        return new ActionAlertViewHolder(view, (TextView) view.findViewById(R.id.textPrimary));
    }

    public static ActionAlertViewHolder createHeader(final View view) {
        return new ActionAlertViewHolder(view, (TextView) view.findViewById(R.id.textPrimary));
    }

    private ActionAlertViewHolder(final View view, final TextView textPrimary) {
        super(view);

        this.textPrimary = textPrimary;
    }
}
