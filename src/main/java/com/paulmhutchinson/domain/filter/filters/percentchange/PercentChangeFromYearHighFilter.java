package com.paulmhutchinson.domain.filter.filters.percentchange;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterType;
import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PercentChangeFromYearHighFilter extends Filter {

    private transient BigDecimal percentChangeFromYearHigh;

    public PercentChangeFromYearHighFilter(BigDecimal percentChangeFromYearHigh) {
        super(FilterType.PERCENT_CHANGE_FROM_YEAR_HIGH.toString(), percentChangeFromYearHigh.toString());
        this.percentChangeFromYearHigh = percentChangeFromYearHigh;
    }

    @Override
    public List<Stock> apply(List<Stock> stocks) {
        try {
            printStatusToLogger();
            return stocks.stream()
                    .filter(s -> isWithinPercentOfYearHigh(s.getQuote().getChangeFromYearHighInPercent()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            printErrorToLogger();
            return new ArrayList<>();
        }
    }

    private boolean isWithinPercentOfYearHigh(final BigDecimal percentChangeFromYearHigh) {
        return percentChangeFromYearHigh.abs().compareTo(percentChangeFromYearHigh) < 0;
    }
}
