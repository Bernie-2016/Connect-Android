package com.berniesanders.connect.screens.news;

import android.view.View;

import com.berniesanders.connect.activities.BaseActivity;
import com.berniesanders.connect.dagger.ActivityScope;
import com.berniesanders.connect.dagger.DaggerNewsComponent;
import com.berniesanders.connect.dagger.NewsComponent;
import com.berniesanders.connect.dagger.NewsModule;
import com.berniesanders.connect.screen.Screen;
import com.berniesanders.connect.screen.ScreenComponent;
import com.berniesanders.connect.screen.ScreenView;

import java.util.Arrays;
import java.util.Collection;

import javax.inject.Inject;

@ActivityScope
public class NewsScreen implements Screen<View> {
    private final BaseActivity mActivity;

    private NewsComponent mComponent;
    private NewsView mView;
    private NewsPresenter mPresenter;

    @Inject
    public NewsScreen(final BaseActivity activity) {
        mActivity = activity;
    }

    @Override
    public void create() {
        mComponent = DaggerNewsComponent.builder()
                .activityComponent(mActivity.getObjectGraph())
                .newsModule(new NewsModule())
                .build();

        mView = mComponent.getNewsView();
        mPresenter = mComponent.getNewsPresenter();
    }

    @Override
    public void destroy() {
        mComponent = null;
        mView = null;
        mPresenter = null;
    }

    @Override
    public ScreenView<View> getView() {
        return mView;
    }

    @Override
    public Collection<ScreenComponent> getComponents() {
        return Arrays.asList(mView, mPresenter);
    }
}
