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
        Sorter sorter = null;
        try {
            Class<?> clazz = Class.forName(sorterType.getSorterClass());
            Constructor<?> constructor = clazz.getConstructor(SortOrder.class);
            sorter = (Sorter) constructor.newInstance(sorterOrder);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return sorter;
    }
}
