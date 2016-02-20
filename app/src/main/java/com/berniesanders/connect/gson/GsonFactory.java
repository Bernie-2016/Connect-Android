package com.berniesanders.connect.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import timber.log.Timber;

public class GsonFactory {
    private static final String TYPE = "type";
    private static final String ID = "id";
    private static final String ATTRIBUTES = "attributes";

    private static final Map<String, Type> TYPE_MAP = new HashMap<>();

    static {
        TYPE_MAP.put(ActionAlertAttributes.TYPE, new TypeToken<ActionAlertAttributes>() {}.getType());
    }

    public static Gson create() {
        return new GsonBuilder()
                .registerTypeAdapter(new TypeToken<JsonApiDataItem>() {}.getType(), (JsonDeserializer<JsonApiDataItem>) (json, typeOfT, context) -> {
                    try {
                        final JsonObject object = json.getAsJsonObject();
                        final String typeString = object.get(TYPE).getAsString();
                        final Type type = TYPE_MAP.get(typeString);

                        if (type != null) {
                            final JsonApiDataItem item = new JsonApiDataItem();

                            item.id = object.get(ID).getAsLong();
                            item.attributes = context.deserialize(object.get(ATTRIBUTES), type);

                            return item;
                        }
                    } catch (final Exception exception) {
                        Timber.d(exception, "");
                    }

                    return null;
                })
                .create();
    }
}
