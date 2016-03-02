package com.berniesanders.connect.util;

import android.net.Uri;
import android.util.Patterns;

public class StringUtil {
    public static String nullAsEmpty(final String string) {
        return string == null ? "" : string;
    }

    public static Long toLong(final String string) {
        return OptionalUtil.notEmpty(string)
                .map(Long::parseLong)
                .orElse(null);
    }

    public static Uri toUri(final String string) {
        return OptionalUtil.notEmpty(string)
                .filter(s -> Patterns.WEB_URL.matcher(s).matches())
                .map(Uri::parse)
                .orElse(null);
    }

    public static String removeScript(final String string) {
        return string.replaceAll("<script>(.|\\r|\\n)*</script>", "").trim();
    }
}
