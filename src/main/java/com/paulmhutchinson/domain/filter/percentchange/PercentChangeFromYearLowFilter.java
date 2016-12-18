package com.paulmhutchinson.domain.filter.percentchange;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterType;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.List;

@Component("PercentChangeFromYearLowFilter")
public class PercentChangeFromYearLowFilter extends Filter {

    private transient BigDecimal percentChangeFromYearLow;

    public PercentChangeFromYearLowFilter() {}

    public PercentChangeFromYearLowFilter(String percentChangeFromYearLow) {
        super(FilterType.PERCENT_CHANGE_FROM_YEAR_LOW, percentChangeFromYearLow);
        this.percentChangeFromYearLow = new BigDecimal(percentChangeFromYearLow);
    }

    @Override
    public void filter(List<Stock> stocks) {
        printStatusToLogger();
        CollectionUtils.filter(stocks, stock -> isWithinPercentOfYearLow(stock.getQuote().getChangeFromYearLowInPercent()));
    }

    private boolean isWithinPercentOfYearLow(final BigDecimal percentChangeFromYearLow) {
        return this.percentChangeFromYearLow.abs().compareTo(percentChangeFromYearLow.abs()) <= 0;
    }
}
