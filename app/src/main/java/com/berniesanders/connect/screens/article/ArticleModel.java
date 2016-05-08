package com.berniesanders.connect.screens.article;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.berniesanders.connect.dagger.ActivityScope;
import com.berniesanders.connect.data.NewsArticle;
import com.berniesanders.connect.hook.ActivityHook;
import com.berniesanders.connect.hook.ActivityHookBuilder;

import javax.inject.Inject;

@ActivityScope
public class ArticleModel {
    public static final String KEY_NEWS_ARTICLE = "NEWS_ARTICLE";

    private NewsArticle mNewsArticle;

    @Inject
    public ArticleModel() {
    }

    public ActivityHook getActivityHook() {
        return new ActivityHookBuilder()
                .onCreate(this::onCreate)
                .build();
    }

    private void onCreate(final AppCompatActivity activity, final Bundle savedInstanceState) {
        mNewsArticle = activity.getIntent().getParcelableExtra(KEY_NEWS_ARTICLE);
    }

    public NewsArticle getNewsArticle() {
        return mNewsArticle;
    }
}
