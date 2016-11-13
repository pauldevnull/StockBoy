package com.paulmhutchinson.domain.filter.percentchange;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterType;
import org.apache.commons.collections4.CollectionUtils;
import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PercentChangeFromYearLowFilter extends Filter {

    private transient BigDecimal percentChangeFromYearLow;

    public PercentChangeFromYearLowFilter(BigDecimal percentChangeFromYearLow) {
        super(FilterType.PERCENT_CHANGE_FROM_YEAR_LOW, percentChangeFromYearLow.toString());
        this.percentChangeFromYearLow = percentChangeFromYearLow;
    }

    @Override
    public Set<Stock> apply(Set<Stock> stocks) {
        try {
            printStatusToLogger();
            Set<Stock> filtered = stocks.stream()
                    .filter(s -> isWithinPercentOfYearLow(s.getQuote().getChangeFromYearLowInPercent()))
                    .collect(Collectors.toSet());
            return new HashSet<>(CollectionUtils.intersection(stocks, filtered));
        } catch (Exception e) {
            printErrorToLogger();
            return new HashSet<>();
        }
    }

    private boolean isWithinPercentOfYearLow(final BigDecimal percentChangeFromYearLow) {
        return percentChangeFromYearLow.abs().compareTo(percentChangeFromYearLow) <= 0;
    }
}
