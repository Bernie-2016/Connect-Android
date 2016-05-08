package com.berniesanders.connect.screens.article;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.berniesanders.connect.R;
import com.berniesanders.connect.dagger.ActivityScope;
import com.berniesanders.connect.data.NewsArticle;
import com.berniesanders.connect.hook.ActivityHook;
import com.berniesanders.connect.hook.ActivityHookBuilder;
import com.berniesanders.connect.util.TimeUtil;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

@ActivityScope
public class ArticleView {
    private final Activity mActivity;
    private final Resources mResources;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.image)
    ImageView mImage;

    @Bind(R.id.title)
    TextView mTitle;

    @Bind(R.id.date)
    TextView mDate;

    @Bind(R.id.body)
    TextView mBody;

    @Inject
    public ArticleView(final Activity activity, final Resources resources) {
        mActivity = activity;
        mResources = resources;
    }

    public ActivityHook getActivityHook() {
        return new ActivityHookBuilder()
                .onCreate(this::onCreate)
                .build();
    }

    private void onCreate(final AppCompatActivity activity, final Bundle savedInstanceState) {
        activity.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.setContentView(R.layout.activity_article);
        ButterKnife.bind(this, activity);

        mToolbar.setTitle(R.string.article_title);
        mToolbar.setTitleTextColor(mResources.getColor(android.R.color.white));
        mToolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        mToolbar.setNavigationOnClickListener(view -> activity.finish());
    }

    public void setNewsArticle(final NewsArticle newsArticle) {
        mTitle.setText(newsArticle.title().trim());
        mDate.setText(TimeUtil.renderTime(DateTime.parse(newsArticle.timestampPublish())));
        mBody.setText(newsArticle.body().trim());

        if (newsArticle.getImageUrl().isPresent()) {
            mImage.setVisibility(View.VISIBLE);

            final Runnable fetchImage = () ->
                    Picasso.with(mActivity)
                            .load(newsArticle.getImageUrl().get())
                            .into(mImage);

            // complete hack to check if the view is "ready"
//            if (mImage.getWidth() == 0) {
                new Handler().post(fetchImage);
//            } else {
//                fetchImage.run();
//            }
        } else {
            mImage.setVisibility(View.GONE);
        }
    }
}
