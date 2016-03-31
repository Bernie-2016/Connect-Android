package com.berniesanders.connect.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.annimon.stream.Optional;

public class ReferenceFragmentManager<T> {
    private final AppCompatActivity mActivity;

    public ReferenceFragmentManager(final AppCompatActivity activity) {
        mActivity = activity;
    }

    public Optional<T> get(final String tag) {
        return Optional.ofNullable((ReferenceFragment<T>) mActivity.getSupportFragmentManager().findFragmentByTag(tag))
                .flatMap(ReferenceFragment::get);
    }

    public void retain(final T reference, final String tag) {
        final Fragment stateFragment = new ReferenceFragment<>(reference);

        mActivity.getSupportFragmentManager().beginTransaction().add(stateFragment, tag).commit();
    }

    public void clear(final String tag) {
        final Fragment stateFragment = mActivity.getSupportFragmentManager().findFragmentByTag(tag);

        if (stateFragment != null) {
            mActivity.getSupportFragmentManager().beginTransaction().remove(stateFragment).commit();
        }
    }
}
