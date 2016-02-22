package com.berniesanders.connect.gson;

import com.berniesanders.connect.data.ActionAlert;

import static com.berniesanders.connect.util.StringUtil.nullAsEmpty;
import static com.berniesanders.connect.util.StringUtil.removeScript;
import static com.berniesanders.connect.util.StringUtil.toLong;
import static com.berniesanders.connect.util.StringUtil.toUri;

public class ActionAlertAttributes implements JsonApiAttributes<ActionAlert> {
    public static final String TYPE = "action_alerts";

    public String title;
    public String body;
    public String short_body;
    public String date;
    public String target_url;
    public String twitter_url;
    public String tweet_id;

    public ActionAlert toValue(final String id) {
        return ActionAlert.builder()
                .id(nullAsEmpty(id))
                .title(nullAsEmpty(title))
                .body(removeScript(nullAsEmpty(body)))
                .shortBody(nullAsEmpty(short_body))
                .date(nullAsEmpty(date))
                .targetUrl(toUri(target_url))
                .twitterUrl(toUri(twitter_url))
                .tweetId(toLong(tweet_id))
                .build();
    }
}
