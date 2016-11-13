package com.paulmhutchinson.util.filter;

import com.google.gson.annotations.SerializedName;
import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.filters.price.MaxPriceFilter;
import com.paulmhutchinson.domain.filter.filters.price.MinPriceFilter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FilterUtil {

    @SerializedName("filters")
    public static final Set<Filter> FILTERS = new HashSet<Filter>(Arrays.asList(
            //new CurrencyFilter(Collections.singletonList("USD")),
            //new ExchangeFilter(Arrays.asList()),
            new MinPriceFilter(new BigDecimal(5)),
            new MaxPriceFilter(new BigDecimal(10))
            //new PercentChangeFromYearHighFilter(),
            //new PercentChangeFromYearLowFilter()
    ));
}
