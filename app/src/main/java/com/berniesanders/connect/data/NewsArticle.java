package com.berniesanders.connect.data;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.annimon.stream.Optional;

import java.io.Serializable;

import auto.parcel.AutoParcel;

import static com.berniesanders.connect.util.OptionalUtil.notEmpty;

@AutoParcel
public abstract class NewsArticle implements Parcelable, Serializable {
    @Nullable protected abstract String bodyHtml();
    @Nullable protected abstract String bodyHtmlNostyle();
    @Nullable protected abstract String bodyMarkdown();
    @Nullable protected abstract String excerpt();
    @Nullable protected abstract String excerptHtml();
    @Nullable protected abstract String imageUrl();
    @Nullable protected abstract String description();
    @Nullable protected abstract String descriptionHtml();
    @Nullable protected abstract String newsCategory();
    @Nullable protected abstract String newsId();
    @Nullable protected abstract String newsType();

    public abstract String articleType();
    public abstract String body();
    public abstract String lang();
    public abstract String objectType();
    public abstract String site();
    public abstract int status();
    public abstract String timestampCreation();
    public abstract String timestampPublish();
    public abstract String title();
    public abstract String url();
    public abstract String uuid();

    public static Builder builder() {
        return new AutoParcel_NewsArticle.Builder();
    }

    public Optional<String> getBodyHtml() {
        return notEmpty(bodyHtml());
    }

    public Optional<String> getBodyHtmlNostyle() {
        return notEmpty(bodyHtmlNostyle());
    }

    public Optional<String> getBodyMarkdown() {
        return notEmpty(bodyMarkdown());
    }

    public Optional<String> getExcerpt() {
        return notEmpty(excerpt());
    }

    public Optional<String> getExcerptHtml() {
        return notEmpty(excerptHtml());
    }

    public Optional<String> getImageUrl() {
        return notEmpty(imageUrl());
    }

    public Optional<String> getDescription() {
        return notEmpty(description());
    }

    public Optional<String> getDescriptionHtml() {
        return notEmpty(descriptionHtml());
    }

    public Optional<String> getNewsCategory() {
        return notEmpty(newsCategory());
    }

    public Optional<String> getNewsId() {
        return notEmpty(newsId());
    }

    public Optional<String> getNewsType() {
        return notEmpty(newsType());
    }

    @AutoParcel.Builder
    public abstract static class Builder {
        public abstract Builder articleType(final String articleType);
        public abstract Builder body(final String body);
        public abstract Builder lang(final String lang);
        public abstract Builder objectType(final String objectType);
        public abstract Builder site(final String site);
        public abstract Builder status(final int status);
        public abstract Builder timestampCreation(final String timestampCreation);
        public abstract Builder timestampPublish(final String timestampPublish);
        public abstract Builder title(final String title);
        public abstract Builder url(final String url);
        public abstract Builder uuid(final String uuid);
        public abstract Builder bodyHtml(final String bodyHtml);
        public abstract Builder bodyHtmlNostyle(final String bodyHtmlNostyle);
        public abstract Builder bodyMarkdown(final String bodyMarkdown);
        public abstract Builder excerpt(final String excerpt);
        public abstract Builder excerptHtml(final String excerptHtml);
        public abstract Builder imageUrl(final String imageUrl);
        public abstract Builder description(final String description);
        public abstract Builder descriptionHtml(final String descriptionHtml);
        public abstract Builder newsCategory(final String newsCategory);
        public abstract Builder newsId(final String newsId);
        public abstract Builder newsType(final String newsType);

        public abstract NewsArticle build();
    }
}
