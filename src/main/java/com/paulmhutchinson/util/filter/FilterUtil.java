package com.paulmhutchinson.util.filter;

import com.google.gson.annotations.SerializedName;
import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.stock.Currency;
import com.paulmhutchinson.domain.filter.currency.CurrencyFilter;
import com.paulmhutchinson.domain.stock.Exchange;
import com.paulmhutchinson.domain.filter.exchange.ExchangeFilter;
import com.paulmhutchinson.domain.filter.price.MaxPriceFilter;
import com.paulmhutchinson.domain.filter.price.MinPriceFilter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FilterUtil {

    @SerializedName("type")
    public static final Set<Filter> FILTERS = new HashSet<Filter>(Arrays.asList(
            new CurrencyFilter(new HashSet<>(Arrays.asList(Currency.values()))),
            new ExchangeFilter(Exchange.getExchanges()),
            new MinPriceFilter(new BigDecimal(5)),
            new MaxPriceFilter(new BigDecimal(15))
            //new PercentChangeFromYearHighFilter(),
            //new PercentChangeFromYearLowFilter()
    ));
}
