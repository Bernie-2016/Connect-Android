package com.berniesanders.connect.util;

import com.annimon.stream.Optional;

public class OptionalUtil {
    public static Optional<String> notEmpty(final String string) {
        if (string == null || string.equals("")) {
            return Optional.empty();
        } else {
            return Optional.of(string);
        }
    }
}
