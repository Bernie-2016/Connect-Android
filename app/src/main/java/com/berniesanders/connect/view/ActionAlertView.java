package com.berniesanders.connect.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.berniesanders.connect.R;
import com.berniesanders.connect.data.ActionAlert;
import com.berniesanders.connect.http.AlertServer;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ActionAlertView extends FrameLayout {
    private ActionAlert mActionAlert;

    @Bind(R.id.web_view)
    WebView mWebView;

    public ActionAlertView(final Context context) {
        super(context);
        init();
    }

    public ActionAlertView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        LayoutInflater.from(getContext()).inflate(R.layout.view_action_alert, this, true);
        ButterKnife.bind(this);

        mWebView.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        mWebView.getSettings().setJavaScriptEnabled(true);
    }

    public void setActionAlert(final ActionAlert actionAlert) {
        if (!actionAlert.equals(mActionAlert)) {
            mActionAlert = actionAlert;
            mWebView.loadUrl(AlertServer.URL + mActionAlert.id());
        }
    }
}
