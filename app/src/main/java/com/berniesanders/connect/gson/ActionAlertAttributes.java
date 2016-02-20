package com.berniesanders.connect.gson;

import com.berniesanders.connect.data.ActionAlert;

import static com.berniesanders.connect.util.StringUtil.nullAsEmpty;
import static com.berniesanders.connect.util.StringUtil.toLong;
import static com.berniesanders.connect.util.StringUtil.toUri;

public class ActionAlertAttributes {
    public static final String TYPE = "action_alerts";

    public String title;
    public String body;
    public String short_body;
    public String date;
    public String target_url;
    public String twitter_url;
    public String tweet_id;

    public ActionAlert toValue() {
        return ActionAlert.builder()
                .title(nullAsEmpty(title))
                .body(nullAsEmpty(body))
                .shortBody(nullAsEmpty(short_body))
                .date(nullAsEmpty(date))
                .targetUrl(toUri(target_url))
                .twitterUrl(toUri(twitter_url))
                .tweetId(toLong(tweet_id))
                .build();
    }
}
