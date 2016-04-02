package com.berniesanders.connect.util;

import android.database.DataSetObserver;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import rx.functions.Action0;
import rx.functions.Action1;

public class Wrap {
    public static OnPageChangeListener onPageSelected(final Action1<Integer> onPageSelected) {
        return new OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(final int position) {
                onPageSelected.call(position);
            }

            @Override
            public void onPageScrollStateChanged(final int state) {
            }
        };
    }
    public static DataSetObserver onDataSetChanged(final Action0 onDataSetChanged) {
        return new DataSetObserver() {
            @Override
            public void onChanged() {
                onDataSetChanged.call();
            }
        };
    }
}
