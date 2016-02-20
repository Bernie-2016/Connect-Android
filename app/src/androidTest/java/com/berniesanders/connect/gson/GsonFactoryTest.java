package com.berniesanders.connect.gson;

import android.util.Pair;

import com.berniesanders.connect.data.ActionAlert;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.List;

public class GsonFactoryTest extends TestCase {
    private static final String ACTION_ALERT_JSON =
            "{\n" +
            "    \"data\": [\n" +
            "        {\n" +
            "            \"attributes\": {\n" +
            "                \"body\": \"When people stand together, and are prepared to fight back, there's nothing we can't accomplish. #AmericanTogether\\n\\n<header>\\r\\n<h2>#AmericaTogether</h2>\\r\\n</header>\\n\\nOn Friday February 19th, we are rolling out another very big social media call to action on Twitter.\",\n" +
            "                \"date\": \"Today!\",\n" +
            "                \"short_body\": \"When people stand together, and are prepared to fight back, there's nothing we can't accomplish. #AmericanTogether\",\n" +
            "                \"target_url\": \"https://www.facebook.com/media/set/?set=a.985805401474464.1073741843.124955570892789&type=3\",\n" +
            "                \"title\": \"Share Now - #AmericaTogether\",\n" +
            "                \"tweet_id\": \"700791753087934464\",\n" +
            "                \"twitter_url\": \"\"\n" +
            "            },\n" +
            "            \"id\": \"139\",\n" +
            "            \"type\": \"action_alerts\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"attributes\": {\n" +
            "                \"body\": \"Movement, (just like the Connect site and many other Bernie tools, websites, and apps) was made by dedicated, hard working, volunteers who believe in Bernie's goals and want to help all volunteers get up-to-the-minute updates directly from the campaign on their mobile phones.  Download the app to stay connected on the go.\\n\\n**[Click Here to Download Movement from the iTunes App Store!](https://geo.itunes.apple.com/us/app/movement-elect-bernie-sanders/id1047784111?mt=8)**\",\n" +
            "                \"date\": \"Download Now!\",\n" +
            "                \"short_body\": \"\\\"Movement - Elect Bernie Sanders in 2016\\\" is now available in the app store for iPhones. This free app gives you access to sharable campaign messages, actions, news, and more.\",\n" +
            "                \"target_url\": \"\",\n" +
            "                \"title\": \"Get Connect Alerts on your iPhone with Movement App\",\n" +
            "                \"tweet_id\": \"695385140122419200\",\n" +
            "                \"twitter_url\": \"\"\n" +
            "            },\n" +
            "            \"id\": \"112\",\n" +
            "            \"type\": \"action_alerts\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"links\": {}\n" +
            "}";

    public void testActionAlertDeserialization() {
        final List<Pair<Long, ActionAlert>> alerts = GsonFactory.create().fromJson(ACTION_ALERT_JSON, JsonApiResponse.class).getActionAlerts();

        Assert.assertEquals(2, alerts.size());
        Assert.assertEquals(700791753087934464L, (long) alerts.get(0).second.tweetId().get());
        Assert.assertEquals(695385140122419200L, (long) alerts.get(1).second.tweetId().get());
    }
}