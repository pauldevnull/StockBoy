package com.paulmhutchinson.domain.filter;

import com.paulmhutchinson.domain.filter.currency.CurrencyFilter;
import com.paulmhutchinson.domain.filter.exchange.ExchangeFilter;
import com.paulmhutchinson.domain.filter.percentchange.PercentChangeFromYearHighFilter;
import com.paulmhutchinson.domain.filter.percentchange.PercentChangeFromYearLowFilter;
import com.paulmhutchinson.domain.filter.price.MaxPriceFilter;
import com.paulmhutchinson.domain.filter.price.MinPriceFilter;
import com.paulmhutchinson.domain.filter.spread.maximum.temporal.MaxDailySpreadFilter;
import com.paulmhutchinson.domain.filter.spread.maximum.temporal.MaxMonthlySpreadFilter;
import com.paulmhutchinson.domain.filter.spread.maximum.temporal.MaxWeeklySpreadFilter;
import com.paulmhutchinson.domain.filter.spread.minimum.temporal.MinDailySpreadFilter;
import com.paulmhutchinson.domain.filter.spread.minimum.temporal.MinMonthlySpreadFilter;
import com.paulmhutchinson.domain.filter.spread.minimum.temporal.MinWeeklySpreadFilter;

public enum FilterType {

    CURRENCY(CurrencyFilter.class),
    EXCHANGE(ExchangeFilter.class),
    MIN_PRICE(MinPriceFilter.class),
    MAX_PRICE(MaxPriceFilter.class),
    MIN_DAILY_SPREAD(MinDailySpreadFilter.class),
    MIN_WEEKLY_SPREAD(MinWeeklySpreadFilter.class),
    MIN_MONTHLY_SPREAD(MinMonthlySpreadFilter.class),
    MAX_DAILY_SPREAD(MaxDailySpreadFilter.class),
    MAX_WEEKLY_SPREAD(MaxWeeklySpreadFilter.class),
    MAX_MONTHLY_SPREAD(MaxMonthlySpreadFilter.class),
    PERCENT_CHANGE_FROM_YEAR_LOW(PercentChangeFromYearLowFilter.class),
    PERCENT_CHANGE_FROM_YEAR_HIGH(PercentChangeFromYearHighFilter.class);

    private Class clazz;

    FilterType(Class clazz) {
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }
}
