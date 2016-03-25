package com.berniesanders.connect.screens.alert;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.TextView;

import com.annimon.stream.Optional;
import com.berniesanders.connect.R;
import com.berniesanders.connect.adapter.actionalert.ActionAlertAdapter;
import com.berniesanders.connect.dagger.AlertsScope;
import com.berniesanders.connect.data.ActionAlert;
import com.berniesanders.connect.screen.ScreenView;
import com.berniesanders.connect.screen.ViewScreenFactory;
import com.berniesanders.connect.util.DimensionUtil;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

@AlertsScope
public class AlertsView implements ScreenView<View>, IAlertsView {
    private final ViewScreenFactory mViewFactory;
    private final DimensionUtil mDimensionUtil;

    private View mView;
    private ActionAlertAdapter mAdapter;
    private List<ActionAlert> mActionAlerts = Collections.emptyList();
    private int mCurrentPosition;

    @Bind(R.id.view_pager)
    ViewPager mViewPager;

    @Bind(R.id.title)
    TextView mTitle;

    @Inject
    public AlertsView(final ViewScreenFactory viewFactory, final DimensionUtil dimensionUtil) {
        mViewFactory = viewFactory;
        mDimensionUtil = dimensionUtil;
    }

    @Override
    public View create() {
        final int horizontalPadding = (int) mDimensionUtil.dpToPx(16);

        mView = mViewFactory.inflate(R.layout.action_alerts_screen);

        ButterKnife.bind(this, mView);

        mAdapter = new ActionAlertAdapter();
        mViewPager.setAdapter(mAdapter);
        mViewPager.setClipToPadding(false);
        mViewPager.setPadding(horizontalPadding, 0, horizontalPadding, 0);

        mViewPager.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(final int position) {
                mCurrentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(final int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    drawAlert(mActionAlerts.get(mCurrentPosition));
                }
            }
        });

        return mView;
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void setActionAlerts(final List<ActionAlert> actionAlerts) {
        mActionAlerts = actionAlerts;
        mAdapter.setActionAlerts(actionAlerts);

        final Optional<ActionAlert> currentAlert = getCurrentActionAlert();

        if (currentAlert.isPresent()) {
            drawAlert(currentAlert.get());
        } else {
            mTitle.setText("");
        }
    }

    private void drawAlert(final ActionAlert alert) {
        mTitle.setText(alert.title());
    }

    private Optional<ActionAlert> getCurrentActionAlert() {
        final int index = mViewPager.getCurrentItem();

        if (index < mActionAlerts.size()) {
            return Optional.of(mActionAlerts.get(index));
        } else {
            return Optional.empty();
        }
    }
}
