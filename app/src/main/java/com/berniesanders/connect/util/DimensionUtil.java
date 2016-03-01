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

    public float dpToPx(final float dp) {
        return dp * mDisplayMetrics.density;
    }

    public float pxToDp(final float px) {
        return px / mDisplayMetrics.density;
    }
}
