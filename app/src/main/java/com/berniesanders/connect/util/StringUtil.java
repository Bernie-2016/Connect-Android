package com.berniesanders.connect.util;

import android.net.Uri;
import android.text.Html;
import android.text.Spanned;
import android.util.Patterns;

import com.annimon.stream.Optional;
import com.commonsware.cwac.anddown.AndDown;

public class StringUtil {
    public static String nullAsEmpty(final String string) {
        return string == null ? "" : string;
    }

    public static Long toLong(final String string) {
        return OptionalUtil.notEmpty(string)
                .map(Long::parseLong)
                .orElse(null);
    }

    public static Optional<Uri> toOptionalUri(final String string) {
        return OptionalUtil.notEmpty(string)
                .filter(s -> Patterns.WEB_URL.matcher(s).matches())
                .map(Uri::parse);
    }

    public static String removeScript(final String string) {
        return string.replaceAll("<script>(.|\\r|\\n)*</script>", "").trim();
    }

    public static Spanned markdownStringToSpanned(final String string) {
        return Html.fromHtml(new AndDown().markdownToHtml(string));
    }
}
