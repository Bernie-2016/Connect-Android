package com.berniesanders.connect.view;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.annimon.stream.function.Consumer;
import com.berniesanders.connect.R;
import com.berniesanders.connect.application.ConnectApplication;
import com.berniesanders.connect.data.NewsArticle;
import com.berniesanders.connect.util.DimensionUtil;
import com.berniesanders.connect.util.TimeUtil;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsArticleView extends CardView {
    private static final int IMAGE_HEIGHT_DP = 100;

    private Consumer<NewsArticle> mOnSelected = newsArticle -> {};
    private NewsArticle mNewsArticle;
    private int mImageHeight;

    @Bind(R.id.layout)
    View mLayout;

    @Bind(R.id.image)
    ImageView mImage;

    @Bind(R.id.title)
    TextView mTitle;

    @Bind(R.id.body)
    TextView mBody;

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

        mLayout.setOnClickListener(view -> mOnSelected.accept(mNewsArticle));
    }

    public void setNewsArticle(final NewsArticle newsArticle) {
        if (!newsArticle.equals(mNewsArticle)) {
            mNewsArticle = newsArticle;
            mTitle.setText(newsArticle.title().trim());
            mDate.setText(TimeUtil.renderTime(DateTime.parse(newsArticle.timestampPublish())));

            if (newsArticle.getImageUrl().isPresent()) {
                mImage.setVisibility(View.VISIBLE);
                mBody.setVisibility(View.GONE);

                final Runnable fetchImage = () ->
                        Picasso.with(getContext())
                                .load(newsArticle.getImageUrl().get())
                                .into(mImage);

                // complete hack to check if the view is "ready"
                if (mImage.getWidth() == 0) {
                    new Handler().post(fetchImage);
                } else {
                    fetchImage.run();
                }
            } else {
                mImage.setVisibility(View.GONE);
                mBody.setVisibility(View.VISIBLE);
                mBody.setText(newsArticle.body().trim());
            }
        }
    }

    public void setOnSelected(final Consumer<NewsArticle> onSelected) {
        mOnSelected = onSelected;
    }
}
