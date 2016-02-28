package com.berniesanders.connect.util;

import android.util.DisplayMetrics;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DimensionUtil {
    private final DisplayMetrics mDisplayMetrics;

    @Inject
    public DimensionUtil(final DisplayMetrics displayMetrics) {
        mDisplayMetrics = displayMetrics;
    }

    public int dpToPx(final int dp) {
        return (int) (dp * mDisplayMetrics.density);
    }

    public int pxToDp(final int px) {
        return (int) (px / mDisplayMetrics.density);
    }
}
