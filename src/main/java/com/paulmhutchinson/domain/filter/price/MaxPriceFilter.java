package com.paulmhutchinson.domain.filter.price;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterType;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.List;

@Component("MaxPriceFilter")
public class MaxPriceFilter extends Filter {

    private transient BigDecimal maxPrice;

    public MaxPriceFilter() {}

    public MaxPriceFilter(String maxPrice) {
        super(FilterType.MAX_PRICE, maxPrice);
        this.maxPrice = new BigDecimal(maxPrice);
    }

    @Override
    public void filter(List<Stock> stocks) {
        printStatusToLogger();
        CollectionUtils.filter(stocks, stock -> isBelowMaxPrice(stock.getQuote().getPrice()));
    }

    private boolean isBelowMaxPrice(final BigDecimal price) {
        return price.compareTo(maxPrice) <= 0;
    }
}
