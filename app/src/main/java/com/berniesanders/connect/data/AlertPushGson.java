package com.berniesanders.connect.data;

public class AlertPushGson {
    public String identifier;
    public String action;

    public AlertPush toValue() {
        return AlertPush.builder()
                .identifier(identifier)
                .action(action)
                .build();
    }
}
