package com.berniesanders.connect.gson;

public interface JsonApiAttributes<T extends Object> {
    T toValue(final String id);
}
