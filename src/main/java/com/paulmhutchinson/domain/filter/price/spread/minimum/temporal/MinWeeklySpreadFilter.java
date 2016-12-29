package com.paulmhutchinson.domain.filter.price.spread.minimum.temporal;

import com.paulmhutchinson.domain.filter.FilterType;
import com.paulmhutchinson.domain.filter.price.spread.minimum.MinSpreadFilter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.List;

@Component("MinWeeklySpreadFilter")
public class MinWeeklySpreadFilter extends MinSpreadFilter {

    private transient BigDecimal minWeeklySpread;

    public MinWeeklySpreadFilter() {}

    public MinWeeklySpreadFilter(String minWeeklySpread) {
        super(FilterType.MIN_WEEKLY_SPREAD, minWeeklySpread);
        this.minWeeklySpread = new BigDecimal(minWeeklySpread);
    }

    @Override
    public void filter(List<Stock> stocks) {
        printStatusToLogger();
        CollectionUtils.filter(stocks, stock -> isGreaterThanMinSpread(getWeeklySpread(stock)));
    }

    @Override
    protected boolean isGreaterThanMinSpread(BigDecimal weeklySpread) {
        return weeklySpread.compareTo(this.minWeeklySpread) <= 0;
    }
}
