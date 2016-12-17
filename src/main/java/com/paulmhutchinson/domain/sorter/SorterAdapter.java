package com.paulmhutchinson.domain.sorter;

import com.google.gson.*;
import com.paulmhutchinson.domain.sorter.price.CurrentPriceSorter;

import javax.swing.*;
import java.lang.reflect.Type;

public class SorterAdapter implements JsonDeserializer<Sorter> {

    @Override
    public Sorter deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject =  json.getAsJsonObject();
        SortType sorterType = SortType.valueOf(jsonObject.get("sorterType").getAsString());
        SortOrder sorterOrder = SortOrder.valueOf(jsonObject.get("sorterOrder").getAsString());

        if (sorterType == SortType.CURRENT_PRICE) {
            return new CurrentPriceSorter(sorterOrder);
        } else {
            return null;
        }
    }
}
