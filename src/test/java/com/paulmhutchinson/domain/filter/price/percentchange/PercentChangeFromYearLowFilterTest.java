package com.paulmhutchinson.domain.filter.price.percentchange;

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
public class PercentChangeFromYearLowFilterTest {

    private static final BigDecimal PERCENT_CHANGE_FROM_YEAR_LOW = new BigDecimal(50);
    private static final Set<String> VALID_SYMBOLS = new HashSet<>(Arrays.asList("A", "B", "C", "D", "E"));
    private List<Stock> stocks = StockFactory.buildDefaultStocks();

    private PercentChangeFromYearLowFilter percentChangeFromYearLowFilter;

    @Before
    public void init() {
        percentChangeFromYearLowFilter = new PercentChangeFromYearLowFilter(PERCENT_CHANGE_FROM_YEAR_LOW.toString());
    }

    @Test
    public void apply_WithListOfStocksAndMaxPrice_ExpectOnlyStocksWithPriceEqualToOrLessThanMaxPrice() {
        List<Stock> validStocks = StockFactory.getStocksFromSymbols(stocks, VALID_SYMBOLS);

        percentChangeFromYearLowFilter.filter(stocks);

        assertTrue(stocks.equals(validStocks));
    }
}
