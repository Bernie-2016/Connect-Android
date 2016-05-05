package com.berniesanders.connect.screens.news;

import android.app.Activity;

import com.berniesanders.connect.dagger.NewsScope;
import com.berniesanders.connect.rx.ActivitySubscriptionManager;
import com.berniesanders.connect.rx.RxError;
import com.berniesanders.connect.screen.ScreenComponent;

import javax.inject.Inject;

@NewsScope
public class NewsPresenter implements ScreenComponent {
    private final NewsModel mModel;
    private final INewsView mView;
    private final ActivitySubscriptionManager mSubscriptionManager;

    @Inject
    public NewsPresenter(final Activity activity, final NewsModel model, final INewsView view) {
        mModel = model;
        mView = view;
        mSubscriptionManager = new ActivitySubscriptionManager(activity);
    }

    @Override
    public void show() {
        mSubscriptionManager.subscribe(
                mModel.getNewsArticles().onErrorResumeNext(RxError.logNever("news feed")),
                mView::setNewsArticles);
    }

    @Override
    public void hide() {
        mSubscriptionManager.unsubscribe();
    }
}
