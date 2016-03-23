package com.berniesanders.connect.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface.OnClickListener;
import android.support.v7.app.AlertDialog.Builder;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.berniesanders.connect.R;
import com.berniesanders.connect.dagger.ActivityScope;

import javax.inject.Inject;

import rx.Observable;
import rx.subjects.PublishSubject;

@ActivityScope
public class PrivacyPolicyDialog {
    private static final String PRIVACY_POLICY_URL = "https://berniesanders.com/privacy-policy/";

    private final Activity mActivity;
    private final PublishSubject<Boolean> mPrivacySubject = PublishSubject.create();

    @Inject
    public PrivacyPolicyDialog(final Activity activity) {
        mActivity = activity;
    }

    public Observable<Boolean> onAgreeToPrivacy() {
        return mPrivacySubject;
    }

    public void show(final boolean agreed) {
        final Dialog dialog = showDialog(agreed, mPrivacySubject);
        final WebView webView = (WebView) dialog.findViewById(R.id.web_view);
        final View progressBar = dialog.findViewById(R.id.progress);

        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                webView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });

        webView.loadUrl(PRIVACY_POLICY_URL);
    }

    private Dialog showDialog(final boolean agreed, final PublishSubject<Boolean> onAgree) {
        final Builder builder = new Builder(mActivity)
                .setCancelable(agreed)
                .setView(R.layout.webview_pivacy_policy)
                .setPositiveButton(getPositiveText(agreed), getPositiveListener(agreed, onAgree));

        if (!agreed) {
            builder.setNegativeButton(R.string.close_app, (dialog, which) -> onAgree.onNext(false));
        }

        return builder.show();
    }

    private int getPositiveText(final boolean agreed) {
        if (agreed) {
            return R.string.close;
        } else {
            return R.string.i_agree;
        }
    }

    private OnClickListener getPositiveListener(final boolean agreed, final PublishSubject<Boolean> onAgree) {
        if (agreed) {
            return null;
        } else {
            return (dialog, which) -> onAgree.onNext(true);
        }
    }
}
