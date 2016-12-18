package com.paulmhutchinson.domain.filter;

import com.paulmhutchinson.domain.filter.currency.CurrencyFilter;
import com.paulmhutchinson.domain.filter.exchange.ExchangeFilter;
import com.paulmhutchinson.domain.filter.percentchange.PercentChangeFromYearHighFilter;
import com.paulmhutchinson.domain.filter.percentchange.PercentChangeFromYearLowFilter;
import com.paulmhutchinson.domain.filter.price.MaxPriceFilter;
import com.paulmhutchinson.domain.filter.price.MinPriceFilter;
import com.paulmhutchinson.domain.filter.spread.minimum.temporal.MinDailySpreadFilter;
import com.paulmhutchinson.domain.filter.spread.minimum.temporal.MinMonthlySpreadFilter;
import com.paulmhutchinson.domain.filter.spread.minimum.temporal.MinWeeklySpreadFilter;

public enum FilterType {

    CURRENCY(CurrencyFilter.class.getName()),
    EXCHANGE(ExchangeFilter.class.getName()),
    MIN_PRICE(MinPriceFilter.class.getName()),
    MAX_PRICE(MaxPriceFilter.class.getName()),
    MIN_DAILY_SPREAD(MinDailySpreadFilter.class.getName()),
    MIN_WEEKLY_SPREAD(MinWeeklySpreadFilter.class.getName()),
    MIN_MONTHLY_SPREAD(MinMonthlySpreadFilter.class.getName()),
    PERCENT_CHANGE_FROM_YEAR_LOW(PercentChangeFromYearLowFilter.class.getName()),
    PERCENT_CHANGE_FROM_YEAR_HIGH(PercentChangeFromYearHighFilter.class.getName());

    private String clazz;

    FilterType(String clazz) {
        this.clazz = clazz;
    }

    public String getClazz() {
        return clazz;
    }
}
