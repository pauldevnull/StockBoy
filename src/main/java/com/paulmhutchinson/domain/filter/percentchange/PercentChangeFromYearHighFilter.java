package com.paulmhutchinson.domain.filter.percentchange;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterType;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.List;

@Component("PercentChangeFromYearHighFilter")
public class PercentChangeFromYearHighFilter extends Filter {

    private transient BigDecimal percentChangeFromYearHigh;

    public PercentChangeFromYearHighFilter() {}

    public PercentChangeFromYearHighFilter(String percentChangeFromYearHigh) {
        super(FilterType.PERCENT_CHANGE_FROM_YEAR_HIGH, percentChangeFromYearHigh);
        this.percentChangeFromYearHigh = new BigDecimal(percentChangeFromYearHigh);
    }

    @Override
    public void filter(List<Stock> stocks) {
        printStatusToLogger();
        CollectionUtils.filter(stocks, stock -> isWithinPercentOfYearHigh(stock.getQuote().getChangeFromYearHighInPercent()));
    }

    private boolean isWithinPercentOfYearHigh(final BigDecimal percentChangeFromYearHigh) {
        return this.percentChangeFromYearHigh.abs().compareTo(percentChangeFromYearHigh.abs()) <= 0;
    }
}
