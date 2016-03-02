package com.berniesanders.connect.data;

import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.annimon.stream.Optional;

import auto.parcel.AutoParcel;

@AutoParcel
public abstract class ActionAlert implements Parcelable {
    @Nullable protected abstract Uri targetUrl();
    @Nullable protected abstract Uri twitterUrl();
    @Nullable protected abstract Long tweetId();

    public abstract String id();
    public abstract String title();
    public abstract String body();
    public abstract String shortBody();
    public abstract String date();

    public static Builder builder() {
        return new AutoParcel_ActionAlert.Builder();
    }

    public Optional<Uri> getTargetUrl() {
        return Optional.ofNullable(targetUrl());
    }

    public Optional<Uri> getTwitterUrl() {
        return Optional.ofNullable(twitterUrl());
    }

    public Optional<Long> getTweetId() {
        return Optional.ofNullable(tweetId());
    }

    @AutoParcel.Builder
    public abstract static class Builder {
        public abstract Builder id(final String id);
        public abstract Builder title(final String title);
        public abstract Builder body(final String body);
        public abstract Builder shortBody(final String shortBody);
        public abstract Builder date(final String date);
        public abstract Builder targetUrl(final Uri targetUrl);
        public abstract Builder twitterUrl(final Uri twitterUrl);
        public abstract Builder tweetId(final Long tweetId);

        public abstract ActionAlert build();
    }
}
