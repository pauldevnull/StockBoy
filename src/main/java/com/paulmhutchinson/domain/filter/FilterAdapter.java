package com.paulmhutchinson.domain.filter;

import com.google.gson.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

public class FilterAdapter implements JsonDeserializer<Filter> {

    @Override
    public Filter deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject =  json.getAsJsonObject();
        FilterType filterType = FilterType.valueOf(jsonObject.get("filterType").getAsString());
        String filterValue = jsonObject.get("filterValue").getAsString();
        try {
            Class<?> clazz = Class.forName(filterType.getClazz().getName());
            Constructor<?> constructor = clazz.getConstructor(String.class);
            return (Filter) constructor.newInstance(filterValue);
        } catch (ReflectiveOperationException e) {
            throw new InvalidFilterRequestException(e.getMessage());
        }
    }

    private static final class InvalidFilterRequestException extends RuntimeException {
        InvalidFilterRequestException(String message) {
            super(message);
        }
    }
}
