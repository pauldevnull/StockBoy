package com.paulmhutchinson.domain.filter.spread.maximum.temporal;

import com.paulmhutchinson.domain.filter.FilterType;
import com.paulmhutchinson.domain.filter.spread.maximum.MaxSpreadFilter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.List;

@Component("MaxMonthlySpreadFilter")
public class MaxMonthlySpreadFilter extends MaxSpreadFilter {

    private transient BigDecimal maxMonthlySpread;

    public MaxMonthlySpreadFilter() {}

    public MaxMonthlySpreadFilter(String maxMonthlySpread) {
        super(FilterType.MAX_MONTHLY_SPREAD, maxMonthlySpread);
        this.maxMonthlySpread = new BigDecimal(maxMonthlySpread);
    }

    @Override
    public void filter(List<Stock> stocks) {
        printStatusToLogger();
        CollectionUtils.filter(stocks, stock -> isLessThanMaxSpread(getMonthlySpread(stock)));
    }

    @Override
    protected boolean isLessThanMaxSpread(BigDecimal monthlySpread) {
        return monthlySpread.compareTo(this.maxMonthlySpread) >= 0;
    }
}
