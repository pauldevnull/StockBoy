package com.paulmhutchinson.util.filter;

import com.google.gson.annotations.SerializedName;
import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.filters.price.MinPriceFilter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class FilterUtil {

    @SerializedName("filters")
    public static final List<Filter> FILTERS = Arrays.asList(
            //new CurrencyFilter(Collections.singletonList("USD")),
            //new ExchangeFilter(Arrays.asList()),
            //new MaxPriceFilter(new BigDecimal(15)),
            new MinPriceFilter(new BigDecimal(10))
            //new PercentChangeFromYearHighFilter(),
            //new PercentChangeFromYearLowFilter()
    );
}
