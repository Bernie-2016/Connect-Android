package com.berniesanders.connect.fragment;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;

import com.annimon.stream.Optional;

public class ReferenceFragment<T> extends Fragment {
    public final T mReference;

    public ReferenceFragment() {
        this(null);
    }

    @SuppressLint("ValidFragment")
    public ReferenceFragment(final T mReference) {
        this.mReference = mReference;
        setRetainInstance(true);
    }

    public Optional<T> get() {
        return Optional.ofNullable(mReference);
    }
}
