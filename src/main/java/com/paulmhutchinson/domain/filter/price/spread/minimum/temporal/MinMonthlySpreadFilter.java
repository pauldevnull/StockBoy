package com.paulmhutchinson.domain.filter.price.spread.minimum.temporal;

import com.paulmhutchinson.domain.filter.FilterType;
import com.paulmhutchinson.domain.filter.price.spread.minimum.MinSpreadFilter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.List;

@Component("MinMonthlySpreadFilter")
public class MinMonthlySpreadFilter extends MinSpreadFilter {

    private transient BigDecimal minMonthlySpread;

    public MinMonthlySpreadFilter() {}

    public MinMonthlySpreadFilter(String minMonthlySpread) {
        super(FilterType.MIN_MONTHLY_SPREAD, minMonthlySpread);
        this.minMonthlySpread = new BigDecimal(minMonthlySpread);
    }

    @Override
    public void filter(List<Stock> stocks) {
        printStatusToLogger();
        CollectionUtils.filter(stocks, stock -> isGreaterThanMinSpread(getMonthlySpread(stock)));
    }

    @Override
    protected boolean isGreaterThanMinSpread(BigDecimal monthlySpread) {
        return monthlySpread.compareTo(this.minMonthlySpread) >= 0;
    }
}
