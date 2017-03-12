package com.paulmhutchinson.domain.filter.price.spread.maximum.temporal;

import com.paulmhutchinson.domain.filter.FilterType;
import com.paulmhutchinson.domain.filter.price.spread.maximum.MaxSpreadFilter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;

import java.util.List;

@Component("MaxDailySpreadFilter")
public class MaxDailySpreadFilter extends MaxSpreadFilter {

    public MaxDailySpreadFilter() {}

    public MaxDailySpreadFilter(String maxDailySpread) { super(FilterType.MAX_DAILY_SPREAD, maxDailySpread); }

    @Override
    public void filter(List<Stock> stocks) {
        printStatusToLogger();
        CollectionUtils.filter(stocks, stock -> isLessThanMaxSpread(getDailySpread(stock)));
    }
 }
