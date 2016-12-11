package com.paulmhutchinson.util.filter;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterType;
import com.paulmhutchinson.domain.filter.currency.CurrencyFilter;
import com.paulmhutchinson.domain.filter.exchange.ExchangeFilter;
import com.paulmhutchinson.domain.filter.percentchange.PercentChangeFromYearHighFilter;
import com.paulmhutchinson.domain.filter.percentchange.PercentChangeFromYearLowFilter;
import com.paulmhutchinson.domain.filter.price.MaxPriceFilter;
import com.paulmhutchinson.domain.filter.price.MinPriceFilter;
import com.paulmhutchinson.domain.stock.Currency;
import com.paulmhutchinson.domain.stock.Exchange;

import java.math.BigDecimal;
import java.util.*;

public final class FilterUtil {

    private FilterUtil() {
        throw new AssertionError();
    }

    public static final Map<FilterType, Filter> FILTERS = new HashMap<FilterType, Filter>() {{
        put(FilterType.CURRENCY, new CurrencyFilter(new HashSet<>(Arrays.asList(Currency.values()))));
        put(FilterType.EXCHANGE, new ExchangeFilter(Exchange.getExchanges()));
        put(FilterType.MIN_PRICE, new MinPriceFilter(new BigDecimal(5)));
        put(FilterType.MAX_PRICE, new MaxPriceFilter(new BigDecimal(5)));
        put(FilterType.PERCENT_CHANGE_FROM_YEAR_HIGH, new PercentChangeFromYearHighFilter(new BigDecimal(10)));
        put(FilterType.PERCENT_CHANGE_FROM_YEAR_LOW, new PercentChangeFromYearLowFilter(new BigDecimal(10)));
    }};
}
