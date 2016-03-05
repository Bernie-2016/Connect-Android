package com.berniesanders.connect.screen;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.berniesanders.connect.dagger.ActivityScope;
import com.berniesanders.connect.dagger.Name;

import javax.inject.Inject;
import javax.inject.Named;

@ActivityScope
public class ViewScreenFactory {
    private final Activity mActivity;
    private final int mContainerId;

    @Inject
    public ViewScreenFactory(final Activity activity, @Named(Name.CONTAINER) final int containerId) {
        mActivity = activity;
        mContainerId = containerId;
    }

    public View inflate(final int layoutId) {
        return LayoutInflater.from(mActivity).inflate(layoutId, (ViewGroup) mActivity.findViewById(mContainerId), false);
    }
}
