package com.paulmhutchinson.domain.filter.spread.maximum.temporal;

import com.paulmhutchinson.domain.filter.FilterType;
import com.paulmhutchinson.domain.filter.spread.maximum.MaxSpreadFilter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.List;

@Component("MaxDailySpreadFilter")
public class MaxDailySpreadFilter extends MaxSpreadFilter {

    private transient BigDecimal maxDailySpread;

    public MaxDailySpreadFilter() {}

    public MaxDailySpreadFilter(String maxDailySpread) {
        super(FilterType.MAX_DAILY_SPREAD, maxDailySpread);
        this.maxDailySpread = new BigDecimal(maxDailySpread);
    }

    @Override
    public void filter(List<Stock> stocks) {
        printStatusToLogger();
        CollectionUtils.filter(stocks, stock -> isLessThanMaxSpread(getDailySpread(stock)));
    }

    @Override
    protected boolean isLessThanMaxSpread(BigDecimal dailySpread) {
        return dailySpread.compareTo(this.maxDailySpread) >= 0;
    }
 }
