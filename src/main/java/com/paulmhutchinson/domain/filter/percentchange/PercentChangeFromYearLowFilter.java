package com.paulmhutchinson.domain.filter.percentchange;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterType;
import org.apache.commons.collections4.CollectionUtils;
import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.Set;

public class PercentChangeFromYearLowFilter extends Filter {

    private transient BigDecimal percentChangeFromYearLow;

    public PercentChangeFromYearLowFilter(BigDecimal percentChangeFromYearLow) {
        super(FilterType.PERCENT_CHANGE_FROM_YEAR_LOW, percentChangeFromYearLow.toString());
        this.percentChangeFromYearLow = percentChangeFromYearLow;
    }

    @Override
    public void apply(Set<Stock> stocks) {
        printStatusToLogger();
        CollectionUtils.filter(stocks, stock -> isWithinPercentOfYearLow(stock.getQuote().getChangeFromYearLowInPercent()));
    }

    private boolean isWithinPercentOfYearLow(final BigDecimal percentChangeFromYearLow) {
        return this.percentChangeFromYearLow.abs().compareTo(percentChangeFromYearLow.abs()) <= 0;
    }
}
