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
import com.berniesanders.connect.data.NewsArticle;
import com.berniesanders.connect.util.DimensionUtil;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsArticleView extends CardView {
    private static final int IMAGE_HEIGHT_DP = 100;

    private NewsArticle mNewsArticle;
    private int mImageHeight;

    @Bind(R.id.image)
    ImageView mImage;

    @Bind(R.id.title)
    TextView mTitle;

    @Bind(R.id.date)
    TextView mDate;

    public NewsArticleView(final Context context) {
        super(context);
        init();
    }

    public NewsArticleView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        final DimensionUtil dimensionUtil = ((ConnectApplication) getContext().getApplicationContext()).getObjectGraph().getDimensionUtil();
        final int padding = (int) dimensionUtil.dpToPx(4);

        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        LayoutInflater.from(getContext()).inflate(R.layout.view_news_article, this, true);
        ButterKnife.bind(this);

        setCardElevation(dimensionUtil.dpToPx(2));
        setRadius(0);
        setUseCompatPadding(true);
        setShadowPadding(padding, padding, padding, padding);
        setPreventCornerOverlap(false);

        mImageHeight = (int) ((ConnectApplication) getContext()
                .getApplicationContext())
                .getObjectGraph()
                .getDimensionUtil()
                .dpToPx(IMAGE_HEIGHT_DP);
    }

    public void setNewsArticle(final NewsArticle newsArticle) {
        if (!newsArticle.equals(mNewsArticle)) {
            mNewsArticle = newsArticle;
            mTitle.setText(mNewsArticle.title());
            mDate.setText(mNewsArticle.timestampPublish());

            if (newsArticle.getImageUrl().isPresent()) {
                mImage.setVisibility(View.VISIBLE);

                post(() -> Picasso.with(getContext())
                        .load(newsArticle.getImageUrl().get())
                        .resize(mImage.getWidth(), mImageHeight)
                        .centerCrop()
                        .into(mImage, new Callback() {
                            @Override
                            public void onSuccess() {
                                mImage.invalidate();
                            }

                            @Override
                            public void onError() {
                            }
                        }));
            } else {
                mImage.setVisibility(View.GONE);
            }
        }
    }
}
