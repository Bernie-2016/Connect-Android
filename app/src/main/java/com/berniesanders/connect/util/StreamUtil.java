package com.berniesanders.connect.util;

import com.annimon.stream.Stream;

public class StreamUtil {
    public static <T> Stream<T> append(final Iterable<T> iterable, final T item) {
        return Stream.concat(Stream.of(iterable), Stream.of(item));
    }
}
