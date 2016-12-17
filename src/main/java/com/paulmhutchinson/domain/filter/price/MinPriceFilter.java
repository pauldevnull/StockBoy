package com.paulmhutchinson.domain.filter.price;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterType;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component("MinPriceFilter")
public class MinPriceFilter extends Filter {

    private transient BigDecimal minPrice;

    public MinPriceFilter() {}

    public MinPriceFilter(BigDecimal minPrice) {
        super(FilterType.MIN_PRICE, minPrice.setScale(2, RoundingMode.FLOOR).toString());
        this.minPrice = minPrice;
    }

    @Override
    public void filter(List<Stock> stocks) {
        printStatusToLogger();
        CollectionUtils.filter(stocks, stock -> isAboveMinPrice(stock.getQuote().getPrice()));
    }

    private boolean isAboveMinPrice(final BigDecimal price) {
        return price.compareTo(minPrice) >= 0;
    }
}
