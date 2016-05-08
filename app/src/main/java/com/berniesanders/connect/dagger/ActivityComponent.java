package com.berniesanders.connect.dagger;

import android.app.Activity;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;

import com.berniesanders.connect.model.ActionAlertsManager;
import com.berniesanders.connect.model.NewsFeedManager;
import com.berniesanders.connect.rx.ActivitySubscriptionManager;
import com.berniesanders.connect.screen.ViewScreenFactory;
import com.berniesanders.connect.screens.article.ArticleActivity;
import com.berniesanders.connect.screens.main.MainActivity;
import com.berniesanders.connect.application.dagger.ApplicationComponent;
import com.berniesanders.connect.screens.detail.DetailActivity;
import com.berniesanders.connect.screens.settings.SettingsActivity;
import com.berniesanders.connect.util.DimensionUtil;

import javax.inject.Named;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(final MainActivity activity);
    void inject(final SettingsActivity activity);
    void inject(final DetailActivity activity);
    void inject(final ArticleActivity activity);

    // application
    ActionAlertsManager getActionAlertsManager();
    NewsFeedManager getNewsFeedManager();
    Resources getResources();
    DimensionUtil getDimensionUtil();

    // activity
    Activity getActivity();
    AppCompatActivity getAppCompatActivity();
    ViewScreenFactory getViewScreenFactory();
    ActivitySubscriptionManager getActivitySubscriptionManager();
    @Named(Name.CONTAINER) int getContainerId();
}
