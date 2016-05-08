package com.berniesanders.connect.util;

import org.joda.time.DateTime;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TimeUtil {
    public static String renderTime(final DateTime time) {
        final DateTime now = DateTime.now();

        if (now.isAfter(time) && now.minusHours(1).isBefore(time)) {
            long minutes = (now.getMillis() - time.getMillis()) / TimeUnit.MINUTES.toMillis(1);

            return String.format(Locale.US, "%d minute%s ago", minutes, minutes == 1 ? "" : "s");
        } else if (now.isAfter(time) && now.minusDays(1).isBefore(time)) {
            long hours = (now.getMillis() - time.getMillis()) / TimeUnit.HOURS.toMillis(1);

            return String.format(Locale.US, "%d hour%s ago", hours, hours == 1 ? "" : "s");
        } else if (now.isAfter(time) && now.minusYears(1).isBefore(time)) {
            return time.toString("MMMM dd", Locale.US);
        } else {
            return time.toString("MMMM dd yyyy", Locale.US);
        }
    }
}
