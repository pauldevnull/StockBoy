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
        Filter filter = null;
        try {
            Class<?> clazz = Class.forName(filterType.getFilterClass());
            Constructor<?> constructor = clazz.getConstructor(String.class);
            filter = (Filter) constructor.newInstance(filterValue);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return filter;
    }
}
