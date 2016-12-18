package com.paulmhutchinson.domain.filter;

public enum FilterType {

    CURRENCY("currency.CurrencyFilter"),
    EXCHANGE("exchange.ExchangeFilter"),
    MIN_PRICE("price.MinPriceFilter"),
    MAX_PRICE("price.MaxPriceFilter"),
    MIN_DAILY_SPREAD("spread.minimum.temporal.MinDailySpreadFilter"),
    MIN_WEEKLY_SPREAD("spread.minimum.temporal.MinWeeklySpreadFilter"),
    MIN_MONTHLY_SPREAD("spread.minimum.temporal.MinMonthlySpreadFilter"),
    PERCENT_CHANGE_FROM_YEAR_LOW("percentchange.PercentChangeFromYearLowFilter"),
    PERCENT_CHANGE_FROM_YEAR_HIGH("percentchange.PercentChangeFromYearHighFilter");

    private String filterClass;

    FilterType(String filterClass) {
        this.filterClass = filterClass;
    }

    public String getFilterClass() {
        return Filter.FILTER_CLASS_PREFIX + filterClass;
    }
}
