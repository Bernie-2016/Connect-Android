package com.berniesanders.connect.util;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;

import java.util.List;
import java.util.Set;

public class StreamUtil {
    public static <T, ID> Stream<T> concatKeepFirst(final List<T> first, final List<T> second, final Function<T, ID> toId) {
        final Set<ID> newIds = Stream.of(first).map(toId).collect(Collectors.toSet());

        return Stream.concat(
                Stream.of(first),
                Stream.of(second).filter(value -> !newIds.contains(toId.apply(value))));
    }
}
