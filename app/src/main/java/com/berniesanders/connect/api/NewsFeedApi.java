package com.berniesanders.connect.api;

import com.berniesanders.connect.data.NewsArticleGson;
import com.berniesanders.connect.gson.HitsResponse;

import retrofit2.http.GET;
import rx.Observable;

public interface NewsFeedApi {
    @GET("articles_en_v1/berniesanders_com/_search")
    Observable<HitsResponse<NewsArticleGson>> getNewsFeed();
}
