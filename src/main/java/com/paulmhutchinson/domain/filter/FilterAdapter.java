package com.paulmhutchinson.domain.filter;

import com.google.gson.*;
import com.paulmhutchinson.domain.filter.percentchange.PercentChangeFromYearLowFilter;
import com.paulmhutchinson.domain.filter.price.MaxPriceFilter;

import java.lang.reflect.Type;
import java.math.BigDecimal;

public class FilterAdapter implements JsonDeserializer<Filter> {

    @Override
    public Filter deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject =  json.getAsJsonObject();
        FilterType filterType = FilterType.valueOf(jsonObject.get("filterType").getAsString());
        JsonElement filterValue = jsonObject.get("filterValue");

        if (filterType == FilterType.MAX_PRICE) {
            return new MaxPriceFilter(new BigDecimal(filterValue.getAsDouble()));
        } else if (filterType == FilterType.PERCENT_CHANGE_FROM_YEAR_LOW) {
            return new PercentChangeFromYearLowFilter(new BigDecimal(filterValue.getAsDouble()));
        } else {
            return null;
        }
    }
}
