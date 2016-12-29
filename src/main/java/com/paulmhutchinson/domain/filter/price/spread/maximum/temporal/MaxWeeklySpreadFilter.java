package com.paulmhutchinson.domain.filter.price.spread.maximum.temporal;

import com.paulmhutchinson.domain.filter.FilterType;
import com.paulmhutchinson.domain.filter.price.spread.maximum.MaxSpreadFilter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.List;

@Component("MaxWeeklySpreadFilter")
public class MaxWeeklySpreadFilter extends MaxSpreadFilter {

    private transient BigDecimal maxWeeklySpread;

    public MaxWeeklySpreadFilter() {}

    public MaxWeeklySpreadFilter(String maxWeeklySpread) {
        super(FilterType.MAX_WEEKLY_SPREAD, maxWeeklySpread);
        this.maxWeeklySpread = new BigDecimal(maxWeeklySpread);
    }

    @Override
    public void filter(List<Stock> stocks) {
        printStatusToLogger();
        CollectionUtils.filter(stocks, stock -> isLessThanMaxSpread(getWeeklySpread(stock)));
    }

    @Override
    protected boolean isLessThanMaxSpread(BigDecimal weeklySpread) {
        return weeklySpread.compareTo(this.maxWeeklySpread) <= 0;
    }
}
