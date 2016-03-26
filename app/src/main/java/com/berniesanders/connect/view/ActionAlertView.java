package com.berniesanders.connect.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.berniesanders.connect.R;
import com.berniesanders.connect.data.ActionAlert;
import com.berniesanders.connect.http.AlertServer;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ActionAlertView extends FrameLayout {
    private ActionAlert mActionAlert;

    @Bind(R.id.progress)
    ProgressBar mProgressBar;

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

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(final WebView view, final String url) {
                mProgressBar.setVisibility(View.GONE);
                mWebView.setVisibility(View.VISIBLE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
                getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                return true;
            }
        });
    }

    public void setActionAlert(final ActionAlert actionAlert) {
        if (!actionAlert.equals(mActionAlert)) {
            mProgressBar.setVisibility(View.VISIBLE);
            mWebView.setVisibility(View.GONE);

            mActionAlert = actionAlert;
            mWebView.loadUrl(AlertServer.URL + mActionAlert.id());
        }
    }
}
