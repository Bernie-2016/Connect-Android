package com.berniesanders.connect.data;

import com.annimon.stream.Optional;

import static com.berniesanders.connect.util.StringUtil.nullAsEmpty;

public class PushGson {
    public String identifier;
    public String action;
    public ApsGson aps;

    public PushAlert toValue() {
        return PushAlert.builder()
                .identifier(nullAsEmpty(identifier))
                .action(nullAsEmpty(action))
                .alert(Optional.ofNullable(aps).map(value -> nullAsEmpty(value.alert)).orElse(""))
                .build();
    }

    public static class ApsGson {
        public String alert;
        public String sound;
    }
}
