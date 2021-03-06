package com.berniesanders.connect.gson;

import com.berniesanders.connect.data.ActionAlert;

import static com.berniesanders.connect.util.StringUtil.nullAsEmpty;
import static com.berniesanders.connect.util.StringUtil.removeScript;
import static com.berniesanders.connect.util.StringUtil.toLong;

public class ActionAlertAttributes implements JsonApiAttributes<ActionAlert> {
    public static final String TYPE = "action_alerts";

    public String title;
    public String body;
    public String body_html;
    public String date;
    public String target_url;
    public String twitter_url;
    public String tweet_id;

    public ActionAlert toValue(final String id) {
        return ActionAlert.builder()
                .id(nullAsEmpty(id))
                .title(nullAsEmpty(title))
                .body(removeScript(nullAsEmpty(body)))
                .bodyHtml(nullAsEmpty(body_html))
                .date(nullAsEmpty(date))
                .targetUrl(target_url)
                .twitterUrl(twitter_url)
                .tweetId(toLong(tweet_id))
                .build();
    }
}
