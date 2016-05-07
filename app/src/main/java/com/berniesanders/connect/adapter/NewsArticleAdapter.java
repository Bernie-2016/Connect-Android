package com.berniesanders.connect.adapter;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;

import com.berniesanders.connect.data.NewsArticle;
import com.berniesanders.connect.recycler.DecorableAdapter;
import com.berniesanders.connect.view.NewsArticleView;

import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.subjects.PublishSubject;

public class NewsArticleAdapter extends DecorableAdapter {
    private final PublishSubject<NewsArticle> mOnSelected = PublishSubject.create();

    private List<NewsArticle> mArticles = Collections.emptyList();

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final NewsArticleView view = new NewsArticleView(parent.getContext());

        view.setOnSelected(mOnSelected::onNext);
        return new ViewHolder(view) {};
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

    public Observable<NewsArticle> onSelected() {
        return mOnSelected;
    }
}
