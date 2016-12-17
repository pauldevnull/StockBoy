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
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class MinPriceFilterTest {

    private static final BigDecimal MIN_PRICE = new BigDecimal(12);
    private static final Set<String> VALID_SYMBOLS = new HashSet<>(Arrays.asList("F", "G", "H", "I", "J"));

    private List<Stock> stocks = StockFactory.buildDefaultStocks();
    private MinPriceFilter minPriceFilter;

    @Before
    public void init() {
        minPriceFilter = new MinPriceFilter(MIN_PRICE);
    }

    @Test
    public void apply_WithListOfStocksAndMinPrice_ExpectOnlyStocksWithPriceEqualToOrGreaterThanMinPrice() {
        List<Stock> validStocks = StockFactory.getStocksFromSymbols(stocks, VALID_SYMBOLS);

        minPriceFilter.filter(stocks);

        assertTrue(stocks.equals(validStocks));
    }
}
