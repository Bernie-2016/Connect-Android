package com.berniesanders.connect.data;

import android.support.annotation.Nullable;

import com.annimon.stream.Optional;

import auto.parcel.AutoParcel;

import static com.berniesanders.connect.util.OptionalUtil.notEmpty;

@AutoParcel
public abstract class AlertPush {
    @Nullable protected abstract String identifier();
    @Nullable protected abstract String action();

    public static Builder builder() {
        return new AutoParcel_AlertPush.Builder();
    }

    public Optional<String> getIdentifier() {
        return notEmpty(identifier());
    }

    public Optional<String> getAction() {
        return notEmpty(action());
    }

    @AutoParcel.Builder
    public abstract static class Builder {
        public abstract Builder identifier(final String identifier);
        public abstract Builder action(final String action);

        public abstract AlertPush build();
    }
}
