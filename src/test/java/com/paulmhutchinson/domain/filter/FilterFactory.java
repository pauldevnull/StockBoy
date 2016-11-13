package com.paulmhutchinson.domain.filter;

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

public class FilterFactory {

    private static final Currency DEFAULT_CURRENCY = Currency.USD;
    private static final Exchange DEFAULT_EXCHANGE = Exchange.DOW;
    private static final BigDecimal DEFAULT_MIN_PRICE = new BigDecimal(5);
    private static final BigDecimal DEFAULT_MAX_PRICE = new BigDecimal(15);
    private static final BigDecimal DEFAULT_PERCENT_CHANGE_FROM_YEAR_HIGH = new BigDecimal(50);
    private static final BigDecimal DEFAULT_PERCENT_CHANGE_FROM_YEAR_LOW = new BigDecimal(50);

    public static Set<Filter> buildDefaultFilters() {
        return new LinkedHashSet<>(Arrays.asList(
                new CurrencyFilter(new HashSet<>(Collections.singletonList(DEFAULT_CURRENCY))),
                new ExchangeFilter(new HashSet<>(Collections.singletonList(DEFAULT_EXCHANGE.getExchange()))),
                new MinPriceFilter(DEFAULT_MIN_PRICE),
                new MaxPriceFilter(DEFAULT_MAX_PRICE),
                new PercentChangeFromYearLowFilter(DEFAULT_PERCENT_CHANGE_FROM_YEAR_LOW),
                new PercentChangeFromYearHighFilter(DEFAULT_PERCENT_CHANGE_FROM_YEAR_HIGH)
        ));
    }
}
