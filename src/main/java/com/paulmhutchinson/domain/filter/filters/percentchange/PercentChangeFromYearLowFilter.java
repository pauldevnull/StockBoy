package com.paulmhutchinson.domain.filter.filters.percentchange;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterType;
import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PercentChangeFromYearLowFilter extends Filter {

    private transient BigDecimal percentChangeFromYearLow;

    public PercentChangeFromYearLowFilter(BigDecimal percentChangeFromYearLow) {
        super(FilterType.PERCENT_CHANGE_FROM_YEAR_LOW.toString(), percentChangeFromYearLow.toString());
        this.percentChangeFromYearLow = percentChangeFromYearLow;
    }

    @Override
    public List<Stock> apply(List<Stock> stocks) {
        try {
            printStatusToLogger();
            return stocks.stream()
                    .filter(s -> isWithinPercentOfYearLow(s.getQuote().getChangeFromYearLowInPercent()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            printErrorToLogger();
            return new ArrayList<>();
        }
    }

    private boolean isWithinPercentOfYearLow(final BigDecimal percentChangeFromYearLow) {
        return percentChangeFromYearLow.abs().compareTo(percentChangeFromYearLow) < 0;
    }
}
