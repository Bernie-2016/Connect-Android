package com.berniesanders.connect.gson;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.berniesanders.connect.data.ActionAlert;

import java.util.List;

public class JsonApiResponse {
    private List<JsonApiDataItem> data;

    public List<ActionAlert> getActionAlerts() {
        return Stream.of(data)
                .filter(item -> item.attributes instanceof ActionAlertAttributes)
                .map(JsonApiResponse::<ActionAlert>itemToValue)
                .collect(Collectors.toList());
    }

    private static <T> T itemToValue(final JsonApiDataItem item) {
        return ((JsonApiAttributes<T>) item.attributes).toValue(item.id);
    }
}
