package com.berniesanders.connect.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.berniesanders.connect.R;
import com.berniesanders.connect.application.ConnectApplication;
import com.berniesanders.connect.data.ActionAlert;
import com.berniesanders.connect.util.DimensionUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.subjects.PublishSubject;

public class ActionAlertView extends CardView {
    private final PublishSubject<ActionAlert> mOnSelected = PublishSubject.create();

    private ActionAlert mActionAlert;

    @Bind(R.id.date)
    TextView mDate;

    @Bind(R.id.title)
    TextView mTitle;

    @Bind(R.id.message)
    TextView mMessage;

    @Bind(R.id.twitter_divider)
    View mTwitterDivider;

    @Bind(R.id.twitter_layout)
    View mTwitterLayout;

    @Bind(R.id.twitter_avatar)
    ImageView mTwitterAvatar;

    @Bind(R.id.twitter_name)
    TextView mTwitterName;

    @Bind(R.id.twitter_user)
    TextView mTwitterUser;

    @Bind(R.id.twitter_tweet)
    TextView mTwitterTweet;

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

        final DimensionUtil dimensionUtil = ((ConnectApplication) getContext().getApplicationContext()).getObjectGraph().getDimensionUtil();
        final int padding = (int) dimensionUtil.dpToPx(4);

        setCardElevation(dimensionUtil.dpToPx(2));
        setRadius(dimensionUtil.dpToPx(4));
        setUseCompatPadding(true);
        setShadowPadding(padding, padding, padding, padding);

        setOnClickListener(view -> {
            if (mActionAlert != null) {
                mOnSelected.onNext(mActionAlert);
            }
        });
    }

    public Observable<ActionAlert> onSelected() {
        return mOnSelected;
    }

    public void setActionAlert(final ActionAlert actionAlert) {
        if (!actionAlert.equals(mActionAlert)) {
            mActionAlert = actionAlert;
            mDate.setText(actionAlert.date());
            mTitle.setText(actionAlert.title());

            final String message = actionAlert.body().replaceAll(">", "").replaceAll(".*https?.*", "").trim();

            if (message.isEmpty()) {
                mMessage.setVisibility(View.GONE);
            } else {
                mMessage.setVisibility(View.VISIBLE);
                mMessage.setText(message);
            }

            if (actionAlert.getTweetId().isPresent()) {
                mTwitterDivider.setVisibility(View.VISIBLE);
                mTwitterLayout.setVisibility(View.VISIBLE);
                mTwitterName.setText("Name");
                mTwitterUser.setText("User");
                mTwitterTweet.setText("Tweet");
            } else {
                mTwitterDivider.setVisibility(View.GONE);
                mTwitterLayout.setVisibility(View.GONE);
            }
        }
    }
}
