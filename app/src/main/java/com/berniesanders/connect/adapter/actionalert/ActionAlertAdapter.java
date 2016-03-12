package com.berniesanders.connect.adapter.actionalert;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.berniesanders.connect.data.ActionAlert;
import com.berniesanders.connect.view.ActionAlertView;

import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.subjects.PublishSubject;

public class ActionAlertAdapter extends PagerAdapter {
    private final PublishSubject<ActionAlert> mSelectedSubject = PublishSubject.create();

    private List<ActionAlert> mAlerts = Collections.emptyList();

    public ActionAlertAdapter() {
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final ActionAlertView view = new ActionAlertView(container.getContext());

        view.setActionAlert(mAlerts.get(position));
        view.onSelected().subscribe(mSelectedSubject);
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
    public boolean isViewFromObject(final View view, final Object object) {
        return view == object;
    }

    public Observable<ActionAlert> getSelectedItems() {
        return mSelectedSubject;
    }

    public void setActionAlerts(final List<ActionAlert> actionAlerts) {
        if (!mAlerts.equals(actionAlerts)) {
            mAlerts = actionAlerts;
            notifyDataSetChanged();
        }
    }
}
