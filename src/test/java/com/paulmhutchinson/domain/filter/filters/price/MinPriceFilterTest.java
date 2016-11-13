package com.paulmhutchinson.domain.filter.filters.price;

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
public class MinPriceFilterTest {

    private static final BigDecimal MIN_PRICE = new BigDecimal(10);
    private static final Set<Stock> STOCKS_AT_OR_ABOVE_PRICE = StockFactory.buildStocksAtOrAbovePrice(MIN_PRICE);
    private static final Set<Stock> STOCKS_AT_OR_BELOW_PRICE = StockFactory.buildStocksAtOrBelowPrice(MIN_PRICE);
    private MinPriceFilter minPriceFilter;

    @Before
    public void init() {
        minPriceFilter = new MinPriceFilter(MIN_PRICE);
    }

    @Test
    public void apply_WithListOfStocksAndMinPrice_ExpectOnlyStocksWithPriceEqualToOrGreaterThanMinPrice() {
        Set<Stock> stocks = new HashSet<>(CollectionUtils.union(STOCKS_AT_OR_BELOW_PRICE, STOCKS_AT_OR_ABOVE_PRICE));

        Set<Stock> filteredStocks = minPriceFilter.apply(new HashSet<>(stocks));

        assertFalse(CollectionUtils.containsAny(filteredStocks, getWithoutPivot()));
        assertTrue(filteredStocks.containsAll(STOCKS_AT_OR_ABOVE_PRICE));
    }

    private Set<Stock> getWithoutPivot() {
        return STOCKS_AT_OR_BELOW_PRICE.stream().filter(s -> !s.getQuote().getPrice().equals(MIN_PRICE)).collect(Collectors.toSet());
    }
}
