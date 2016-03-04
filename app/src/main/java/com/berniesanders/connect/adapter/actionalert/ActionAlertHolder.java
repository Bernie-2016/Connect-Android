package com.berniesanders.connect.adapter.actionalert;

import android.support.v7.widget.RecyclerView.ViewHolder;

import com.berniesanders.connect.data.ActionAlert;
import com.berniesanders.connect.view.ActionAlertView;

import rx.functions.Action1;

public class ActionAlertHolder extends ViewHolder {
    private Action1<ActionAlert> mOnSelected = actionAlert -> {};

    public ActionAlertHolder(final ActionAlertView actionAlertView) {
        super(actionAlertView);

        actionAlertView.onSelected().subscribe(actionAlert -> mOnSelected.call(actionAlert));
    }

    private ActionAlertView getView() {
        return (ActionAlertView) itemView;
    }

    public void setActionAlert(final ActionAlert actionAlert) {
        getView().setActionAlert(actionAlert);
    }

    public void setOnSelected(final Action1<ActionAlert> onSelected) {
        mOnSelected = onSelected;
    }
}
