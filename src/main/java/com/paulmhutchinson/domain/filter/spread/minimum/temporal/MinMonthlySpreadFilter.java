package com.paulmhutchinson.domain.filter.spread.minimum.temporal;

import com.paulmhutchinson.domain.filter.FilterType;
import com.paulmhutchinson.domain.filter.spread.minimum.MinSpreadFilter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;
import yahoofinance.quotes.stock.StockQuote;

import java.math.BigDecimal;
import java.util.Set;

@Component("MonthlySpreadFilter")
public class MinMonthlySpreadFilter extends MinSpreadFilter {

    private transient BigDecimal minMonthlySpread;

    public MinMonthlySpreadFilter() {}

    public MinMonthlySpreadFilter(BigDecimal minMonthlySpread) {
        super(FilterType.MIN_MONTHLY_SPREAD, minMonthlySpread.toString());
        this.minMonthlySpread = minMonthlySpread;
    }

    @Override
    public void filter(Set<Stock> stocks) {
        printStatusToLogger();
        CollectionUtils.filter(stocks, stock -> isGreaterThanMinSpread(getSpread(stock)));
    }

    @Override
    protected BigDecimal getSpread(Stock stock) {
        StockQuote quote = stock.getQuote();
        return quote.getDayHigh().subtract(quote.getDayLow());
    }

    @Override
    protected boolean isGreaterThanMinSpread(BigDecimal monthlySpread) {
        return monthlySpread.compareTo(minMonthlySpread) >= 0;
    }
}
