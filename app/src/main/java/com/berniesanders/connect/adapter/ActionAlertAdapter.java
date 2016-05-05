package com.berniesanders.connect.adapter;

import android.support.v4.view.PagerAdapter;
import android.util.LruCache;
import android.view.View;
import android.view.ViewGroup;

import com.berniesanders.connect.data.ActionAlert;
import com.berniesanders.connect.view.ActionAlertView;

import java.util.Collections;
import java.util.List;

public class ActionAlertAdapter extends PagerAdapter {
    private LruCache<ActionAlert, ActionAlertView> mViewCache = new LruCache<>(30);
    private List<ActionAlert> mAlerts = Collections.emptyList();

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final ActionAlert actionAlert = mAlerts.get(position);

        ActionAlertView view = mViewCache.get(actionAlert);

        if (view == null) {
            view = new ActionAlertView(container.getContext());
            view.setActionAlert(actionAlert);
            mViewCache.put(actionAlert, view);
        }

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mAlerts.size();
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view == object;
    }

    public void setActionAlerts(final List<ActionAlert> actionAlerts) {
        if (!mAlerts.equals(actionAlerts)) {
            mAlerts = actionAlerts;
            mViewCache.evictAll();
            notifyDataSetChanged();
        }
    }
}
