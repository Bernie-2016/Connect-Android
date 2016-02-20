package com.berniesanders.connect.gson;

import android.util.Pair;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.berniesanders.connect.data.ActionAlert;

import java.util.List;

public class JsonApiResponse {
    private List<JsonApiDataItem> data;

    public List<Pair<Long, ActionAlert>> getActionAlerts() {
        return getDataItems()
                .filter(item -> item.attributes instanceof ActionAlertAttributes)
                .map(item -> Pair.create(item.id, ((ActionAlertAttributes) item.attributes).toValue()))
                .collect(Collectors.toList());
    }

    private Stream<JsonApiDataItem> getDataItems() {
        return Stream.of(data).filter(item -> item != null);
    }
}
