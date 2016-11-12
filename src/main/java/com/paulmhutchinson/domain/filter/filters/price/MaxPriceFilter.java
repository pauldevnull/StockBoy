package com.paulmhutchinson.domain.filter.filters.price;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterType;
import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MaxPriceFilter extends Filter {

    private transient BigDecimal maxPrice;

    public MaxPriceFilter(BigDecimal maxPrice) {
        super(FilterType.MAX_PRICE.toString(), maxPrice.toString());
        this.maxPrice = maxPrice;
    }

    @Override
    public List<Stock> apply(List<Stock> stocks) {
        try {
            printStatusToLogger();
            return stocks.stream()
                    .filter(s -> isBelowMaxPrice(s.getQuote().getPrice()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            printErrorToLogger();
            return new ArrayList<>();
        }
    }

    private boolean isBelowMaxPrice(final BigDecimal price) {
        return price.compareTo(maxPrice) < 0;
    }
}
