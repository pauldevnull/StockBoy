package com.paulmhutchinson.domain.filter.price;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterType;
import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class MaxPriceFilter extends Filter {

    private transient BigDecimal maxPrice;

    public MaxPriceFilter(BigDecimal maxPrice) {
        super(FilterType.MAX_PRICE, maxPrice.toString());
        this.maxPrice = maxPrice;
    }

    @Override
    public Set<Stock> apply(Set<Stock> stocks) {
        try {
            printStatusToLogger();
            return stocks.stream()
                    .filter(s -> isBelowMaxPrice(s.getQuote().getPrice()))
                    .collect(Collectors.toSet());
        } catch (Exception e) {
            printErrorToLogger();
            return new HashSet<>();
        }
    }

    private boolean isBelowMaxPrice(final BigDecimal price) {
        return price.compareTo(maxPrice) <= 0;
    }
}
