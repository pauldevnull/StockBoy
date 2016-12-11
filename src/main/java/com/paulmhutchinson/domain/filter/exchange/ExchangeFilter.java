package com.paulmhutchinson.domain.filter.exchange;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterType;
import org.apache.commons.collections4.CollectionUtils;
import yahoofinance.Stock;

import java.util.Set;

public class ExchangeFilter extends Filter {

    private transient Set<String> exchanges;

    public ExchangeFilter(Set<String> exchanges) {
        super(FilterType.EXCHANGE, exchanges.toString());
        this.exchanges = exchanges;
    }

    @Override
    public void filter(Set<Stock> stocks) {
        printStatusToLogger();
        CollectionUtils.filter(stocks, stock -> isValidExchange(stock.getStockExchange()));
    }

    private boolean isValidExchange(final String exchange) {
        return exchanges.contains(exchange);
    }
}
