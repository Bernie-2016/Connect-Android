package com.berniesanders.connect.screens.article;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.berniesanders.connect.dagger.ActivityScope;
import com.berniesanders.connect.hook.ActivityHook;
import com.berniesanders.connect.hook.ActivityHookBuilder;

import java.util.Arrays;
import java.util.Collection;

import javax.inject.Inject;

@ActivityScope
public class ArticlePresenter {
    private final ArticleModel mModel;
    private final ArticleView mView;

    @Inject
    public ArticlePresenter(final ArticleModel model, final ArticleView view) {
        mModel = model;
        mView = view;
    }

    public Collection<ActivityHook> getActivityHooks() {
        return Arrays.asList(
                mView.getActivityHook(),
                mModel.getActivityHook(),
                new ActivityHookBuilder()
                        .onCreate(this::onCreate)
                        .build());
    }

    private void onCreate(final AppCompatActivity activity, final Bundle savedInstanceState) {
        mView.setNewsArticle(mModel.getNewsArticle());
    }
}
