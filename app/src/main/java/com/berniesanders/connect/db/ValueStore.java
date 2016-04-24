package com.berniesanders.connect.db;

import com.annimon.stream.Optional;

import rx.Observable;

public interface ValueStore<T> {
    Observable<Optional<T>> read();
    void write(final T value);
}
