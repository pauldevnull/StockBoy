package com.paulmhutchinson.domain.filter.price.spread.minimum.temporal;

import com.paulmhutchinson.domain.filter.FilterType;
import com.paulmhutchinson.domain.filter.price.spread.minimum.MinSpreadFilter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.List;

@Component("MinDailySpreadFilter")
public class MinDailySpreadFilter extends MinSpreadFilter {

    private transient BigDecimal minDailySpread;

    public MinDailySpreadFilter() {}

    public MinDailySpreadFilter(String minDailySpread) {
        super(FilterType.MIN_DAILY_SPREAD, minDailySpread);
        this.minDailySpread = new BigDecimal(minDailySpread);
    }

    @Override
    public void filter(List<Stock> stocks) {
        printStatusToLogger();
        CollectionUtils.filter(stocks, stock -> isGreaterThanMinSpread(getDailySpread(stock)));
    }

    @Override
    protected boolean isGreaterThanMinSpread(BigDecimal dailySpread) {
        return dailySpread.compareTo(this.minDailySpread) >= 0;
    }
 }
