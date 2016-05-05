package com.berniesanders.connect.model;

import com.annimon.stream.Optional;
import com.berniesanders.connect.api.NewsFeedApi;
import com.berniesanders.connect.data.NewsArticle;
import com.berniesanders.connect.data.NewsArticleGson;
import com.berniesanders.connect.db.GsonValueStore;
import com.berniesanders.connect.gson.HitsResponse;
import com.berniesanders.connect.db.GsonDb;
import com.berniesanders.connect.util.TimeToLive;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class NewsFeedManager {
    private static final int SOFT_MAX_ARTICLES = 100;

    private final MergingModelManager<HitsResponse<NewsArticleGson>, NewsArticleGson, NewsArticle, String> mDelegate;

    @Inject
    public NewsFeedManager(final NewsFeedApi newsFeedApi, final GsonDb gsonDb) {
        mDelegate = new MergingModelManager<>(
                SOFT_MAX_ARTICLES,
                new GsonValueStore<>(gsonDb, GsonDb.NEWS_ARTICLES, new TypeToken<List<NewsArticleGson>>() {}),
                new TimeToLive(TimeUnit.MINUTES, 10),
                NewsArticleGson::fromValue,
                NewsArticleGson::toValue,
                NewsArticle::uuid,
                newsFeedApi::getNewsFeed,
                observable -> observable
                        .map(HitsResponse::getHits)
                        .flatMap(Observable::from)
                        .map(NewsArticleGson::toValue)
                        .toList());
    }

    public Optional<NewsArticle> getNewsArticleById(final String id) {
        return mDelegate.getDataById(id);
    }

    public Observable<List<NewsArticle>> getNewsArticles() {
        return mDelegate.getData();
    }

    public Observable<List<NewsArticle>> requestNewsArticles() {
        return mDelegate.request();
    }
}
