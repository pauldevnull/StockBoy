package com.paulmhutchinson.domain.filter.price;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterType;
import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class MinPriceFilter extends Filter {

    private transient BigDecimal minPrice;

    public MinPriceFilter(BigDecimal minPrice) {
        super(FilterType.MIN_PRICE, minPrice.toString());
        this.minPrice = minPrice;
    }

    @Override
    public Set<Stock> apply(Set<Stock> stocks) {
        try {
            printStatusToLogger();
            return stocks.stream()
                    .filter(s -> isAboveMinPrice(s.getQuote().getPrice()))
                    .collect(Collectors.toSet());
        } catch (Exception e) {
            printErrorToLogger();
            return new HashSet<>();
        }
    }

    private boolean isAboveMinPrice(final BigDecimal price) {
        return price.compareTo(minPrice) >= 0;
    }
}
