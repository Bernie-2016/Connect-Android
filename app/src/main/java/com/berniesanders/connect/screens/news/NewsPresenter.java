package com.berniesanders.connect.screens.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.berniesanders.connect.dagger.NewsScope;
import com.berniesanders.connect.rx.ActivitySubscriptionManager;
import com.berniesanders.connect.rx.RxError;
import com.berniesanders.connect.screen.ScreenComponent;
import com.berniesanders.connect.screens.article.ArticleActivity;
import com.berniesanders.connect.screens.article.ArticleModel;

import javax.inject.Inject;

@NewsScope
public class NewsPresenter implements ScreenComponent {
    private final Activity mActivity;
    private final NewsModel mModel;
    private final INewsView mView;
    private final ActivitySubscriptionManager mSubscriptionManager;

    @Inject
    public NewsPresenter(final Activity activity, final NewsModel model, final INewsView view) {
        mActivity = activity;
        mModel = model;
        mView = view;
        mSubscriptionManager = new ActivitySubscriptionManager(activity);
    }

    @Override
    public void show() {
        mSubscriptionManager.subscribe(
                mModel.getNewsArticles().onErrorResumeNext(RxError.logNever("news feed")),
                mView::setNewsArticles);

        mSubscriptionManager.subscribe(
                mView.onSelected().onErrorResumeNext(RxError.logNever("news feed")),
                newsArticle -> {
                    final Intent intent = new Intent(mActivity, ArticleActivity.class);
                    final Bundle bundle = new Bundle();

                    bundle.putParcelable(ArticleModel.KEY_NEWS_ARTICLE, newsArticle);
                    intent.putExtras(bundle);
                    mActivity.startActivity(intent);
                });
    }

    @Override
    public void hide() {
        mSubscriptionManager.unsubscribe();
    }
}
