package com.paulmhutchinson.domain.filter.price;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterType;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component("MaxPriceFilter")
public class MaxPriceFilter extends Filter {

    private transient BigDecimal maxPrice;

    public MaxPriceFilter() {}

    public MaxPriceFilter(BigDecimal maxPrice) {
        super(FilterType.MAX_PRICE, maxPrice.setScale(2, RoundingMode.FLOOR).toString());
        this.maxPrice = maxPrice;
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
