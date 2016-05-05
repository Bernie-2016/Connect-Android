package com.berniesanders.connect.dagger;

import com.berniesanders.connect.screens.news.NewsView;
import com.berniesanders.connect.screens.news.INewsView;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsModule {
    public NewsModule() {
    }

    @Provides
    public INewsView provideNewsView(final NewsView newsView) {
        return newsView;
    }
}
