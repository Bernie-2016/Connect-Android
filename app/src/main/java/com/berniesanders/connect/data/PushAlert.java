package com.berniesanders.connect.data;

import android.os.Parcelable;

import java.io.Serializable;

import auto.parcel.AutoParcel;

@AutoParcel
public abstract class PushAlert implements Parcelable, Serializable {
    public abstract String identifier();
    public abstract String action();
    public abstract String alert();

    public static Builder builder() {
        return new AutoParcel_PushAlert.Builder();
    }

    @AutoParcel.Builder
    public abstract static class Builder {
        public abstract Builder identifier(final String identifier);
        public abstract Builder action(final String action);
        public abstract Builder alert(final String alert);

        public abstract PushAlert build();
    }
}
