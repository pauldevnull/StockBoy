package com.paulmhutchinson.domain.filter.price;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterType;
import org.apache.commons.collections4.CollectionUtils;
import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.Set;

public class MaxPriceFilter extends Filter {

    private transient BigDecimal maxPrice;

    public MaxPriceFilter(BigDecimal maxPrice) {
        super(FilterType.MAX_PRICE, maxPrice.toString());
        this.maxPrice = maxPrice;
    }

    @Override
    public void apply(Set<Stock> stocks) {
        printStatusToLogger();
        CollectionUtils.filter(stocks, stock -> isBelowMaxPrice(stock.getQuote().getPrice()));
    }

    private boolean isBelowMaxPrice(final BigDecimal price) {
        return price.compareTo(maxPrice) <= 0;
    }
}
