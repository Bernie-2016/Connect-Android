package com.berniesanders.connect.data;

import static com.berniesanders.connect.util.StringUtil.nullAsEmpty;
import static com.berniesanders.connect.util.StringUtil.removeScript;

public class ActionAlertGson {
    public String id;
    public String title;
    public String body;
    public String bodyHtml;
    public String date;
    public String targetUrl;
    public String twitterUrl;
    public Long tweetId;

    public ActionAlert toValue() {
        return ActionAlert.builder()
                .id(nullAsEmpty(id))
                .title(nullAsEmpty(title))
                .body(removeScript(nullAsEmpty(body)))
                .bodyHtml(nullAsEmpty(bodyHtml))
                .date(nullAsEmpty(date))
                .targetUrl(targetUrl)
                .twitterUrl(twitterUrl)
                .tweetId(tweetId)
                .build();
    }

    public static ActionAlertGson fromValue(final ActionAlert actionAlert) {
        final ActionAlertGson gson = new ActionAlertGson();

        gson.id = actionAlert.id();
        gson.title = actionAlert.title();
        gson.body = actionAlert.body();
        gson.bodyHtml = actionAlert.bodyHtml();
        gson.date = actionAlert.date();
        gson.targetUrl = actionAlert.targetUrl();
        gson.twitterUrl = actionAlert.twitterUrl();
        gson.tweetId = actionAlert.tweetId();

        return gson;
    }
}
