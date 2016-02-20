package com.berniesanders.connect.data;

import android.net.Uri;

import com.annimon.stream.Optional;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class ActionAlert {
    public abstract String id();
    public abstract String title();
    public abstract String body();
    public abstract String shortBody();
    public abstract String date();
    public abstract Optional<Uri> targetUrl();
    public abstract Optional<Uri> twitterUrl();
    public abstract Optional<Long> tweetId();

    public static Builder builder() {
        return new AutoValue_ActionAlert.Builder();
    }

    public abstract Builder toBuilder();

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(final String id);
        public abstract Builder title(final String title);
        public abstract Builder body(final String body);
        public abstract Builder shortBody(final String shortBody);
        public abstract Builder date(final String date);
        public abstract Builder targetUrl(final Optional<Uri> targetUrl);
        public abstract Builder twitterUrl(final Optional<Uri> twitterUrl);
        public abstract Builder tweetId(final Optional<Long> tweetId);

        public abstract ActionAlert build();
    }
}
