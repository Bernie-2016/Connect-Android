package com.berniesanders.connect.data;

import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.annimon.stream.Optional;

import java.io.Serializable;

import auto.parcel.AutoParcel;

import static com.berniesanders.connect.util.StringUtil.toOptionalUri;

@AutoParcel
public abstract class ActionAlert implements Parcelable, Serializable {
    @Nullable protected abstract String targetUrl();
    @Nullable protected abstract String twitterUrl();
    @Nullable protected abstract Long tweetId();

    public abstract String id();
    public abstract String title();
    public abstract String body();
    public abstract String bodyHtml();
    public abstract String date();

    public static Builder builder() {
        return new AutoParcel_ActionAlert.Builder();
    }

    public Optional<Uri> getTargetUrl() {
        return toOptionalUri(targetUrl());
    }

    public Optional<Uri> getTwitterUrl() {
        return toOptionalUri(twitterUrl());
    }

    public Optional<Long> getTweetId() {
        return Optional.ofNullable(tweetId());
    }

    @AutoParcel.Builder
    public abstract static class Builder {
        public abstract Builder id(final String id);
        public abstract Builder title(final String title);
        public abstract Builder body(final String body);
        public abstract Builder bodyHtml(final String bodyHtml);
        public abstract Builder date(final String date);
        public abstract Builder targetUrl(final String targetUrl);
        public abstract Builder twitterUrl(final String twitterUrl);
        public abstract Builder tweetId(final Long tweetId);

        public abstract ActionAlert build();
    }
}
