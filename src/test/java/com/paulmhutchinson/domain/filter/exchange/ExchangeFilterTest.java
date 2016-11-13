package com.paulmhutchinson.domain.filter.exchange;

import com.paulmhutchinson.domain.stock.Exchange;
import com.paulmhutchinson.domain.stock.StockFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import yahoofinance.Stock;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ExchangeFilterTest {

    private static final Set<String> EXCHANGES = new HashSet<>(Collections.singletonList(Exchange.DOW.getExchange()));
    private static final Set<String> VALID_SYMBOLS = new HashSet<>(Arrays.asList("A", "B", "C", "D", "E"));
    private Set<Stock> stocks = StockFactory.buildDefaultStocks();
    private ExchangeFilter exchangeFilter;

    @Before
    public void init() {
        exchangeFilter = new ExchangeFilter(EXCHANGES);
    }

    @Test
    public void apply_WithListOfStocksAndExchanges_ExpectOnlyStocksWithValidExchanges() {
        Set<Stock> validStocks = StockFactory.getStocksFromSymbols(stocks, VALID_SYMBOLS);

        exchangeFilter.apply(stocks);

        assertTrue(stocks.equals(validStocks));
    }
}
