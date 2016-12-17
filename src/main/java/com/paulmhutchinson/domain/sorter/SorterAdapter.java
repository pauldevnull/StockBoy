package com.paulmhutchinson.domain.sorter;

import com.google.gson.*;
import com.paulmhutchinson.domain.sorter.price.CurrentPriceSorter;

import javax.swing.*;
import java.lang.reflect.Type;

public class SorterAdapter implements JsonDeserializer<Sorter> {

    @Override
    public Sorter deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject =  json.getAsJsonObject();
        SortType sortType = SortType.valueOf(jsonObject.get("sortType").getAsString());
        SortOrder sortOrder = SortOrder.valueOf(jsonObject.get("sortOrder").getAsString());

        if (sortType == SortType.CURRENT_PRICE) {
            return new CurrentPriceSorter(sortOrder);
        } else {
            return null;
        }
    }
}
