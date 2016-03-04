package com.berniesanders.connect.route;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.annimon.stream.Optional;
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
        final Optional<Long> tweetId = mActionAlert.getTweetId();
        final Optional<Uri> twitterUrl = mActionAlert.getTwitterUrl();

        if (tweetId.isPresent()) {
            return activity -> {
                boolean success = false;

                try {
                    activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://status?status_id=" + tweetId.get())));
                    success = true;
                } catch (final Exception exception) {
                }

                if (success) return;

                try {
                    if (twitterUrl.isPresent()) {
                        showTwitterUrl(activity, twitterUrl.get());
                        success = true;
                    }
                } catch (final Exception exception) {
                }

                if (success) return;

                showDetails(activity);
            };
        } else if (twitterUrl.isPresent()) {
            return activity -> showTwitterUrl(activity, twitterUrl.get());
        } else {
            return this::showDetails;
        }
    }

    private void showTwitterUrl(final Activity activity, final Uri twitterUrl) {
        activity.startActivity(new Intent(Intent.ACTION_VIEW, twitterUrl));
    }

    private void showDetails(final Activity activity) {
        final Intent intent = new Intent(activity, DetailActivity.class);

        intent.putExtra(DetailModel.KEY_ACTION_ALERT, mActionAlert);
        activity.startActivity(intent);
    }
}
