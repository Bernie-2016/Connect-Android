package com.berniesanders.connect.util;

import android.os.SystemClock;

import com.annimon.stream.Optional;

import java.util.concurrent.TimeUnit;

public class TimeToLive {
    private final long mDuration;
    private final TimeProvider mTimeProvider;

    public long mExpiration;

    public TimeToLive(final TimeUnit timeUnit, final long numUnits) {
        this(timeUnit, numUnits, SystemClock::uptimeMillis);
    }

    public TimeToLive(final TimeUnit timeUnit, final long numUnits, final TimeProvider timeProvider) {
        mDuration = timeUnit.toMillis(numUnits);
        mTimeProvider = timeProvider;
        mExpiration = mTimeProvider.getTime();
    }

    public TimeToLive reset() {
        mExpiration = mTimeProvider.getTime() + mDuration;
        return this;
    }

    public boolean isValid() {
        return mTimeProvider.getTime() < mExpiration;
    }

    public <T> Optional<T> ifValid(final T value) {
        if (isValid()) {
            return Optional.of(value);
        } else {
            return Optional.empty();
        }
    }

    public interface TimeProvider {
        long getTime();
    }
}
