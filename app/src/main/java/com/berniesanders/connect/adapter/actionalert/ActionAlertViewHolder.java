package com.berniesanders.connect.adapter.actionalert;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

import com.annimon.stream.Optional;
import com.berniesanders.connect.R;
import com.berniesanders.connect.view.ActionAlertView;

public class ActionAlertViewHolder extends ViewHolder {
    public final Optional<ActionAlertView> actionAlertView;
    public final Optional<TextView> textPrimary;

    public static ActionAlertViewHolder createActionAlert(final ActionAlertView actionAlertView) {
        return new ActionAlertViewHolder(actionAlertView);
    }

    public static ActionAlertViewHolder createHeader(final View view) {
        return new ActionAlertViewHolder(view, (TextView) view.findViewById(R.id.textPrimary));
    }

    private ActionAlertViewHolder(final ActionAlertView actionAlertView) {
        super(actionAlertView);

        this.actionAlertView = Optional.of(actionAlertView);
        this.textPrimary = Optional.empty();
    }

    private ActionAlertViewHolder(final View view, final TextView textPrimary) {
        super(view);

        this.actionAlertView = Optional.empty();
        this.textPrimary = Optional.of(textPrimary);
    }
}
