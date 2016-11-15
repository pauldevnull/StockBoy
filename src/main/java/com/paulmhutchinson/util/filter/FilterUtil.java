package com.paulmhutchinson.util.filter;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.percentchange.PercentChangeFromYearLowFilter;
import com.paulmhutchinson.domain.filter.price.MaxPriceFilter;
import com.paulmhutchinson.domain.filter.price.MinPriceFilter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class FilterUtil {

    private FilterUtil() {
        throw new AssertionError();
    }

    public static final Set<Filter> FILTERS = new HashSet<Filter>(Arrays.asList(
            //new CurrencyFilter(new HashSet<>(Arrays.asList(Currency.values()))),
            //new ExchangeFilter(Exchange.getExchanges()),
            new MinPriceFilter(new BigDecimal(5)),
            new MaxPriceFilter(new BigDecimal(15)),
            //new PercentChangeFromYearHighFilter(),
            new PercentChangeFromYearLowFilter(new BigDecimal(10))
    ));
}