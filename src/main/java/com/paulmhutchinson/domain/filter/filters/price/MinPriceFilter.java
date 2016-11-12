package com.paulmhutchinson.domain.filter.filters.price;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterType;
import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MinPriceFilter extends Filter {

    private transient BigDecimal minPrice;

    public MinPriceFilter(BigDecimal minPrice) {
        super(FilterType.MIN_PRICE.toString(), minPrice.toString());
        this.minPrice = minPrice;
    }

    @Override
    public List<Stock> apply(List<Stock> stocks) {
        try {
            printStatusToLogger();
            return stocks.stream()
                    .filter(s -> isAboveMinPrice(s.getQuote().getPrice()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            printErrorToLogger();
            return new ArrayList<>();
        }
    }

    private boolean isAboveMinPrice(final BigDecimal price) {
        return price.compareTo(minPrice) > 0;
    }
}
