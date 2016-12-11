package com.paulmhutchinson.domain.filter.percentchange;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterType;
import org.apache.commons.collections4.CollectionUtils;
import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.Set;

public class PercentChangeFromYearHighFilter extends Filter {

    private transient BigDecimal percentChangeFromYearHigh;

    public PercentChangeFromYearHighFilter(BigDecimal percentChangeFromYearHigh) {
        super(FilterType.PERCENT_CHANGE_FROM_YEAR_HIGH, percentChangeFromYearHigh.toString());
        this.percentChangeFromYearHigh = percentChangeFromYearHigh;
    }

    @Override
    public void filter(Set<Stock> stocks) {
        printStatusToLogger();
        CollectionUtils.filter(stocks, stock -> isWithinPercentOfYearHigh(stock.getQuote().getChangeFromYearHighInPercent()));
    }

    private boolean isWithinPercentOfYearHigh(final BigDecimal percentChangeFromYearHigh) {
        return this.percentChangeFromYearHigh.abs().compareTo(percentChangeFromYearHigh.abs()) <= 0;
    }
}
