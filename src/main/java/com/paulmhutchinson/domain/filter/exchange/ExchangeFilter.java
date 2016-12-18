package com.paulmhutchinson.domain.filter.exchange;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterType;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component("ExchangeFilter")
public class ExchangeFilter extends Filter {

    private transient Set<String> exchanges;

    public ExchangeFilter() {}

    public ExchangeFilter(String exchanges) {
        super(FilterType.EXCHANGE, exchanges);
        this.exchanges = new HashSet<>(Arrays.asList(exchanges.split(",")));
    }

    @Override
    public void filter(List<Stock> stocks) {
        printStatusToLogger();
        CollectionUtils.filter(stocks, stock -> isValidExchange(stock.getStockExchange()));
    }

    private boolean isValidExchange(final String exchange) {
        return exchanges.contains(exchange);
    }
}
