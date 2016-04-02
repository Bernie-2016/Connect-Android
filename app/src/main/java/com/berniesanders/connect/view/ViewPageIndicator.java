package com.berniesanders.connect.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.annimon.stream.Stream;
import com.berniesanders.connect.R;
import com.berniesanders.connect.util.Wrap;

public class ViewPageIndicator extends LinearLayout {
    private int mPosition;
    private Drawable mSelected;
    private Drawable mDeselected;

    public ViewPageIndicator(final Context context) {
        super(context);
        init();
    }

    public ViewPageIndicator(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setGravity(Gravity.CENTER);
        setOrientation(LinearLayout.HORIZONTAL);
        mSelected = getContext().getResources().getDrawable(R.drawable.indicator_item_selected);
        mDeselected = getContext().getResources().getDrawable(R.drawable.indicator_item_deselected);

        if (isInEditMode()) {
            setupPages(3);
        }
    }

    public void attach(final ViewPager pager, final PagerAdapter adapter) {
        pager.addOnPageChangeListener(Wrap.onPageSelected(this::updateSelection));
        adapter.registerDataSetObserver(Wrap.onDataSetChanged(() -> setupPages(adapter.getCount())));
    }

    private void setupPages(final int numPages) {
        removeAllViews();

        Stream.ofRange(0, numPages).forEach(index -> {
            final View indicatorItem = LayoutInflater.from(getContext()).inflate(R.layout.indicator_item, ViewPageIndicator.this, false);

            if (index == mPosition) {
                select(indicatorItem);
            } else {
                deselect(indicatorItem);
            }

            addView(indicatorItem);
        });
    }

    private void updateSelection(final int position) {
        if (getChildCount() > Math.max(mPosition, position)) {
            deselect(getChildAt(mPosition));
            mPosition = position;
            select(getChildAt(position));
        }
    }

    private void select(final View indicatorItem) {
        indicatorItem.setBackgroundDrawable(mSelected);
    }

    private void deselect(final View indicatorItem) {
        indicatorItem.setBackgroundDrawable(mDeselected);
    }
}
