package com.paulmhutchinson.domain.filter.price;

import com.paulmhutchinson.domain.stock.StockFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class MaxPriceFilterTest {

    private static final BigDecimal MAX_PRICE = new BigDecimal(10);
    private static final Set<String> VALID_SYMBOLS = new HashSet<>(Arrays.asList("A", "B", "C", "D", "E"));

    private Set<Stock> stocks = StockFactory.buildDefaultStocks();
    private MaxPriceFilter maxPriceFilter;

    @Before
    public void init() {
        maxPriceFilter = new MaxPriceFilter(MAX_PRICE);
    }

    @Test
    public void apply_WithListOfStocksAndMaxPrice_ExpectOnlyStocksWithPriceEqualToOrLessThanMaxPrice() {
        Set<Stock> validStocks = StockFactory.getStocksFromSymbols(stocks, VALID_SYMBOLS);

        maxPriceFilter.apply(stocks);

        assertTrue(stocks.equals(validStocks));
    }
}
