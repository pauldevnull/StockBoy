package com.paulmhutchinson.domain.filter.spread;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterType;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;
import yahoofinance.quotes.stock.StockQuote;

import java.math.BigDecimal;

@Component("SpreadFilter")
public abstract class SpreadFilter extends Filter {

    public SpreadFilter() {}

    public SpreadFilter(FilterType filterType, String filterValue) {
        super(filterType, filterValue);
    }

    protected BigDecimal getSpread(Stock stock) {
        StockQuote quote = stock.getQuote();
        return quote.getDayHigh().subtract(quote.getDayLow());
    }
}
