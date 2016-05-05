package com.berniesanders.connect.screens.news;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.berniesanders.connect.R;
import com.berniesanders.connect.dagger.NewsScope;
import com.berniesanders.connect.data.NewsArticle;
import com.berniesanders.connect.screen.ScreenView;
import com.berniesanders.connect.screen.ViewScreenFactory;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

@NewsScope
public class NewsView implements ScreenView<View>, INewsView {
    private final ViewScreenFactory mViewFactory;

    private View mView;
//    private NewsArticleAdapter mAdapter;
    private List<NewsArticle> mNewsArticles = Collections.emptyList();

    @Bind(R.id.progress)
    ProgressBar mProgressBar;

    @Bind(R.id.recycler)
    RecyclerView mRecyclerView;

    @Inject
    public NewsView(final ViewScreenFactory viewFactory) {
        mViewFactory = viewFactory;
    }

    @Override
    public View create() {
        mView = mViewFactory.inflate(R.layout.news_feed_screen);

        ButterKnife.bind(this, mView);

//        mAdapter = new NewsArticleAdapter();
//        mRecyclerView.setAdapter(mAdapter);

        return mView;
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void setNewsArticles(final List<NewsArticle> newsArticles) {
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
//        mAdapter.setNewsArticles(newsArticles);
    }
}
