package com.paulmhutchinson.domain.filter.price;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterType;
import org.apache.commons.collections4.CollectionUtils;
import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.Set;

public class MinPriceFilter extends Filter {

    private transient BigDecimal minPrice;

    public MinPriceFilter(BigDecimal minPrice) {
        super(FilterType.MIN_PRICE, minPrice.toString());
        this.minPrice = minPrice;
    }

    @Override
    public void apply(Set<Stock> stocks) {
        try {
            printStatusToLogger();
            CollectionUtils.filter(stocks, stock -> isAboveMinPrice(stock.getQuote().getPrice()));
        } catch (Exception e) {
            printErrorToLogger();
        }
    }

    private boolean isAboveMinPrice(final BigDecimal price) {
        return price.compareTo(minPrice) >= 0;
    }
}
