package com.paulmhutchinson.domain.filter.filters.percentchange;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterType;
import org.apache.commons.collections4.CollectionUtils;
import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PercentChangeFromYearHighFilter extends Filter {

    private transient BigDecimal percentChangeFromYearHigh;

    public PercentChangeFromYearHighFilter(BigDecimal percentChangeFromYearHigh) {
        super(FilterType.PERCENT_CHANGE_FROM_YEAR_HIGH.toString(), percentChangeFromYearHigh.toString());
        this.percentChangeFromYearHigh = percentChangeFromYearHigh;
    }

    @Override
    public Set<Stock> apply(Set<Stock> stocks) {
        try {
            printStatusToLogger();
            Set<Stock> filtered = stocks.stream()
                    .filter(s -> isWithinPercentOfYearHigh(s.getQuote().getChangeFromYearHighInPercent()))
                    .collect(Collectors.toSet());
            return new HashSet<>(CollectionUtils.intersection(stocks, filtered));
        } catch (Exception e) {
            printErrorToLogger();
            return new HashSet<>();
        }
    }

    private boolean isWithinPercentOfYearHigh(final BigDecimal percentChangeFromYearHigh) {
        return percentChangeFromYearHigh.abs().compareTo(percentChangeFromYearHigh) < 0;
    }
}
