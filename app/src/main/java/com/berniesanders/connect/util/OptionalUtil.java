package com.berniesanders.connect.util;

import com.annimon.stream.Optional;

public class OptionalUtil {
    public static Optional<String> notEmpty(final String value) {
        if (StringUtil.isEmpty(value)) {
            return Optional.empty();
        } else {
            return Optional.of(value);
        }
    }
}
