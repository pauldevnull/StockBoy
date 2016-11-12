package com.paulmhutchinson.domain.filter.filters.exchange;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterType;
import yahoofinance.Stock;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExchangeFilter extends Filter {

    private transient List<String> exchanges;

    public ExchangeFilter(List<String> exchanges) {
        super(FilterType.EXCHANGE.toString(), exchanges.toString());
        this.exchanges = exchanges;
    }

    @Override
    public List<Stock> apply(List<Stock> stocks) {
        try {
            printStatusToLogger();
            return stocks.stream()
                    .filter(s -> isValidExchange(s.getStockExchange()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            printErrorToLogger();
            return new ArrayList<>();
        }
    }

    private boolean isValidExchange(final String exchange) {
        return exchanges.contains(exchange);
    }
}
