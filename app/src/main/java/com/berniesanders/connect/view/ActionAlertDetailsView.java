package com.berniesanders.connect.view;

import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.berniesanders.connect.R;
import com.berniesanders.connect.data.ActionAlert;
import com.commonsware.cwac.anddown.AndDown;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ActionAlertDetailsView extends FrameLayout {
    @Bind(R.id.title)
    TextView mTitle;

    @Bind(R.id.body)
    TextView mBody;

    public ActionAlertDetailsView(final Context context) {
        super(context);
        init();
    }

    public ActionAlertDetailsView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_action_alert_details, this, true);
        ButterKnife.bind(this);
        mBody.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public ActionAlertDetailsView setActionAlert(final ActionAlert actionAlert) {
        mTitle.setText(actionAlert.title());
        mBody.setText(Html.fromHtml(new AndDown().markdownToHtml(actionAlert.body())));
        return this;
    }
}
