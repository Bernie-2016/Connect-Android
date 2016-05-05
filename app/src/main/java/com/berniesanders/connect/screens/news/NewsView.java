package com.berniesanders.connect.screens.news;

import android.content.res.Resources;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.berniesanders.connect.R;
import com.berniesanders.connect.adapter.NewsArticleAdapter;
import com.berniesanders.connect.dagger.NewsScope;
import com.berniesanders.connect.data.NewsArticle;
import com.berniesanders.connect.screen.ScreenView;
import com.berniesanders.connect.screen.ViewScreenFactory;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

@NewsScope
public class NewsView implements ScreenView<View>, INewsView {
    private final ViewScreenFactory mViewFactory;
    private final Resources mResources;

    private View mView;
    private NewsArticleAdapter mAdapter;

    @Bind(R.id.progress)
    ProgressBar mProgressBar;

    @Bind(R.id.recycler)
    RecyclerView mRecyclerView;

    @Inject
    public NewsView(final ViewScreenFactory viewFactory, final Resources resources) {
        mViewFactory = viewFactory;
        mResources = resources;
    }

    @Override
    public View create() {
        mView = mViewFactory.inflate(R.layout.news_feed_screen);

        ButterKnife.bind(this, mView);

        mRecyclerView.setLayoutManager(new GridLayoutManager(mView.getContext(), mResources.getInteger(R.integer.grid_span)));
        mAdapter = new NewsArticleAdapter();
        mRecyclerView.setAdapter(mAdapter);

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
        mAdapter.setNewsArticles(newsArticles);
    }
}
