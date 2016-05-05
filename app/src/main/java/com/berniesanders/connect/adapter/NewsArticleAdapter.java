package com.berniesanders.connect.adapter;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;

import com.berniesanders.connect.data.NewsArticle;
import com.berniesanders.connect.recycler.DecorableAdapter;
import com.berniesanders.connect.view.NewsArticleView;

import java.util.Collections;
import java.util.List;

public class NewsArticleAdapter extends DecorableAdapter {
    private List<NewsArticle> mArticles = Collections.emptyList();

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new ViewHolder(new NewsArticleView(parent.getContext())) {};
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        ((NewsArticleView) holder.itemView).setNewsArticle(mArticles.get(position));
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    @Override
    public int getMaxViewType() {
        return 0;
    }

    public void setNewsArticles(final List<NewsArticle> newsArticles) {
        if (!mArticles.equals(newsArticles)) {
            mArticles = newsArticles;
            notifyDataSetChanged();
        }
    }
}
