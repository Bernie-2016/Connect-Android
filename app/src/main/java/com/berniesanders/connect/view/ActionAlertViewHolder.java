package com.berniesanders.connect.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.berniesanders.connect.R;

/**
 * Created by John on 2/23/16.
 */
public class ActionAlertViewHolder extends RecyclerView.ViewHolder {

    public final TextView alertTitleTV;

    public ActionAlertViewHolder(View view) {
        super(view);
        this.alertTitleTV = (TextView)view.findViewById(R.id.alertTitle);
    }

}
