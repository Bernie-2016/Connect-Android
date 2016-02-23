package com.berniesanders.connect.rx;

import android.os.Looper;

final class Assertions {
    public static void assertUiThread() {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException("must subscribe on main thread");
        }
    }
}
