package com.berniesanders.connect.route;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;
import com.berniesanders.connect.data.ActionAlert;
import com.berniesanders.connect.screens.detail.DetailActivity;
import com.berniesanders.connect.screens.detail.DetailModel;

import rx.functions.Action1;

public class ActionAlertRouter {
    private final ActionAlert mActionAlert;

    public ActionAlertRouter(final ActionAlert actionAlert) {
        mActionAlert = actionAlert;
    }

    public Action1<Activity> selectAction() {
        return handleFirstSuccess(
                tryHandle(tweetIdHandler(mActionAlert.getTweetId())),
                tryHandle(urlHandler(mActionAlert.getTwitterUrl())),
                tryHandle(urlHandler(mActionAlert.getTargetUrl())),
                showDetails(mActionAlert));
    }

    private static Action1<Activity> handleFirstSuccess(final Function<Activity, Boolean>... predicates) {
        return activity -> Stream.of(predicates).anyMatch(predicate -> predicate.apply(activity));
    }

    private static Action1<Activity> tweetIdHandler(final Optional<Long> tweetId) {
        return activity -> activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://status?status_id=" + tweetId.get())));
    }

    private static Action1<Activity> urlHandler(final Optional<Uri> url) {
        return activity -> activity.startActivity(new Intent(Intent.ACTION_VIEW, url.get()));
    }

    private static Function<Activity, Boolean> tryHandle(final Action1<Activity> action) {
        return activity -> {
            try {
                action.call(activity);
                return true;
            } catch (final Exception exception) {
            }

            return false;
        };
    }

    private static Function<Activity, Boolean> showDetails(final ActionAlert actionAlert) {
        return activity -> {
            final Intent intent = new Intent(activity, DetailActivity.class);

            intent.putExtra(DetailModel.KEY_ACTION_ALERT, (Parcelable) actionAlert);
            activity.startActivity(intent);
            return true;
        };
    }
}
