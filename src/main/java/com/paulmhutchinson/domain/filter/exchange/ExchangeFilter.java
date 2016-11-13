package com.paulmhutchinson.domain.filter.exchange;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterType;
import org.apache.commons.collections4.CollectionUtils;
import yahoofinance.Stock;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ExchangeFilter extends Filter {

    private transient Set<String> exchanges;

    public ExchangeFilter(Set<String> exchanges) {
        super(FilterType.EXCHANGE, exchanges.toString());
        this.exchanges = exchanges;
    }

    @Override
    public Set<Stock> apply(Set<Stock> stocks) {
        try {
            printStatusToLogger();
            Set<Stock> filtered = stocks.stream()
                    .filter(s -> isValidExchange(s.getStockExchange()))
                    .collect(Collectors.toSet());
            return new HashSet<>(CollectionUtils.intersection(stocks, filtered));
        } catch (Exception e) {
            printErrorToLogger();
            return new HashSet<>();
        }
    }

    private boolean isValidExchange(final String exchange) {
        return exchanges.contains(exchange);
    }
}
