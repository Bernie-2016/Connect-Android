package com.berniesanders.connect.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.berniesanders.connect.R;
import com.berniesanders.connect.application.ConnectApplication;
import com.berniesanders.connect.data.ActionAlert;
import com.berniesanders.connect.http.AlertServer;
import com.berniesanders.connect.util.DimensionUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.subjects.PublishSubject;

public class ActionAlertView extends FrameLayout {
    private final PublishSubject<ActionAlert> mOnSelected = PublishSubject.create();

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

//        final DimensionUtil dimensionUtil = ((ConnectApplication) getContext().getApplicationContext()).getObjectGraph().getDimensionUtil();
//        final int padding = (int) dimensionUtil.dpToPx(4);

//        setPadding(0, padding, 0, padding);
//        setCardElevation(dimensionUtil.dpToPx(2));
//        setRadius(dimensionUtil.dpToPx(4));
//        setUseCompatPadding(true);
//        setShadowPadding(padding, padding, padding, padding);

        mWebView.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        mWebView.getSettings().setJavaScriptEnabled(true);
//        setOnClickListener(view -> {
//            if (mActionAlert != null) {
//                mOnSelected.onNext(mActionAlert);
//            }
//        });
    }

    public Observable<ActionAlert> onSelected() {
        return mOnSelected;
    }

    public void setActionAlert(final ActionAlert actionAlert) {
        if (!actionAlert.equals(mActionAlert)) {
            mActionAlert = actionAlert;
            mWebView.loadUrl(AlertServer.URL + mActionAlert.id());
        }
    }
}
