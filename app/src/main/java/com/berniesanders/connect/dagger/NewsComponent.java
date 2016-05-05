package com.berniesanders.connect.dagger;

import com.berniesanders.connect.screens.news.NewsPresenter;
import com.berniesanders.connect.screens.news.NewsView;

import dagger.Component;

@NewsScope
@Component(dependencies = ActivityComponent.class, modules = NewsModule.class)
public interface NewsComponent {
    NewsView getNewsView();
    NewsPresenter getNewsPresenter();
}
