package com.berniesanders.connect.db;

import com.annimon.stream.Optional;
import com.google.gson.reflect.TypeToken;

import rx.Observable;

public class GsonValueStore<T> implements ValueStore<T> {
    private final GsonDb mGsonDb;
    private final String mKey;
    private final TypeToken<T> mTypeToken;

    public GsonValueStore(final GsonDb gsonDb, final String key, final TypeToken<T> typeToken) {
        mGsonDb = gsonDb;
        mKey = key;
        mTypeToken = typeToken;
    }

    @Override
    public Observable<Optional<T>> read() {
        return mGsonDb.read(mKey, mTypeToken);
    }

    @Override
    public void write(final T value) {
        mGsonDb.write(mKey, value);
    }
}
