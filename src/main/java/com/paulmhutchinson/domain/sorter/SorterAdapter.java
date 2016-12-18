package com.paulmhutchinson.domain.sorter;

import com.google.gson.*;

import javax.swing.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

public class SorterAdapter implements JsonDeserializer<Sorter> {

    @Override
    public Sorter deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject =  json.getAsJsonObject();
        SorterType sorterType = SorterType.valueOf(jsonObject.get("sorterType").getAsString());
        SortOrder sorterOrder = SortOrder.valueOf(jsonObject.get("sorterOrder").getAsString());
        try {
            Class<?> clazz = Class.forName(sorterType.getSorterClass().getName());
            Constructor<?> constructor = clazz.getConstructor(SortOrder.class);
            return (Sorter) constructor.newInstance(sorterOrder);
        } catch (ReflectiveOperationException e) {
            throw new InvalidSorterRequestException(e.getMessage());
        }
    }

    private static final class InvalidSorterRequestException extends RuntimeException {
        public InvalidSorterRequestException(String message) {
            super(message);
        }
    }
}
