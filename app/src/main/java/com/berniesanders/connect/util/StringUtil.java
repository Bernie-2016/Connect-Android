package com.berniesanders.connect.util;

import android.net.Uri;
import android.util.Patterns;

import com.annimon.stream.Optional;

public class StringUtil {
    public static String nullAsEmpty(final String string) {
        return string == null ? "" : string;
    }

    public static Optional<Long> toLong(final String string) {
        return OptionalUtil.notEmpty(string).map(Long::parseLong);
    }

    public static Optional<Uri> toUri(final String string) {
        return OptionalUtil.notEmpty(string)
                .filter(s -> Patterns.WEB_URL.matcher(s).matches())
                .map(Uri::parse);
    }

    public static String removeScript(final String string) {
        return string.replaceAll("<script>(.|\\r|\\n)*</script>", "").trim();
    }
}
