package com.paulmhutchinson.domain.filter.price;

import com.paulmhutchinson.domain.stock.StockFactory;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class MaxPriceFilterTest {

    private static final BigDecimal MAX_PRICE = new BigDecimal(10);
    private static final Set<Stock> STOCKS_AT_OR_ABOVE_PRICE = StockFactory.buildStocksAtOrAbovePrice(MAX_PRICE);
    private static final Set<Stock> STOCKS_AT_OR_BELOW_PRICE = StockFactory.buildStocksAtOrBelowPrice(MAX_PRICE);
    private MaxPriceFilter maxPriceFilter;

    @Before
    public void init() {
        maxPriceFilter = new MaxPriceFilter(MAX_PRICE);
    }

    @Test
    public void apply_WithListOfStocksAndMaxPrice_ExpectOnlyStocksWithPriceEqualToOrLessThanMaxPrice() {
        Set<Stock> stocks = new HashSet<>(CollectionUtils.union(STOCKS_AT_OR_BELOW_PRICE, STOCKS_AT_OR_ABOVE_PRICE));

        Set<Stock> filteredStocks = maxPriceFilter.apply(new HashSet<>(stocks));

        assertFalse(CollectionUtils.containsAny(filteredStocks, getWithoutPivot()));
        assertTrue(filteredStocks.containsAll(STOCKS_AT_OR_BELOW_PRICE));
    }

    private Set<Stock> getWithoutPivot() {
        return STOCKS_AT_OR_ABOVE_PRICE.stream().filter(s -> !s.getQuote().getPrice().equals(MAX_PRICE)).collect(Collectors.toSet());
    }
}
