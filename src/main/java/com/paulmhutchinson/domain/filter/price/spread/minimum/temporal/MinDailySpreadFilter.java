package com.paulmhutchinson.domain.filter.price.spread.minimum.temporal;

import com.paulmhutchinson.domain.filter.FilterType;
import com.paulmhutchinson.domain.filter.price.spread.minimum.MinSpreadFilter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;

import java.util.List;

@Component("MinDailySpreadFilter")
public class MinDailySpreadFilter extends MinSpreadFilter {

    public MinDailySpreadFilter() {}

    public MinDailySpreadFilter(String minDailySpread) {
        super(FilterType.MIN_DAILY_SPREAD, minDailySpread);
    }

    @Override
    public void filter(List<Stock> stocks) {
        printStatusToLogger();
        CollectionUtils.filter(stocks, stock -> isGreaterThanMinSpread(getDailySpread(stock)));
    }
 }
