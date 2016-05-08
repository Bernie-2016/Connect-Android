package com.berniesanders.connect.screens.news;

import com.berniesanders.connect.dagger.NewsScope;
import com.berniesanders.connect.data.NewsArticle;

import java.util.List;

import rx.Observable;

@NewsScope
public interface INewsView {
    void setNewsArticles(List<NewsArticle> newsArticles);
    Observable<NewsArticle> onSelected();
}
