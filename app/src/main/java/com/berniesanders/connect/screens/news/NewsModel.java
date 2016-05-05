package com.berniesanders.connect.screens.news;

import com.berniesanders.connect.dagger.NewsScope;
import com.berniesanders.connect.data.NewsArticle;
import com.berniesanders.connect.model.NewsFeedManager;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

@NewsScope
public class NewsModel {
    private final NewsFeedManager mNewsFeedManager;

    @Inject
    public NewsModel(final NewsFeedManager newsFeedManager) {
        mNewsFeedManager = newsFeedManager;
    }

    public Observable<List<NewsArticle>> getNewsArticles() {
        return mNewsFeedManager.getNewsArticles();
    }
}
