package com.paulmhutchinson.domain.filter.exchange;

import com.paulmhutchinson.domain.stock.StockFactory;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import yahoofinance.Stock;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ExchangeFilterTest {

    private static final Set<String> EXCHANGES = new HashSet<>(Arrays.asList(Exchange.DOW.getExchange(), Exchange.NASDAQ.getExchange(), Exchange.SP.getExchange()));
    private static final Set<Stock> STOCKS = StockFactory.buildStocks(10);
    private ExchangeFilter exchangeFilter;

    @Before
    public void init() {
        exchangeFilter = new ExchangeFilter(EXCHANGES);
    }

    @Test
    public void apply_WithListOfStocksAndExchanges_ExpectOnlyStocksWithValidExchanges() {
        Set<Stock> filteredStocks = exchangeFilter.apply(new HashSet<>(STOCKS));

        assertFalse(CollectionUtils.containsAny(filteredStocks, getInValidStocks()));
        assertTrue(filteredStocks.containsAll(getValidStocks()));
    }

    private Set<Stock> getValidStocks() {
        return STOCKS.stream()
                .filter(s -> isValid(s.getStockExchange()))
                .collect(Collectors.toSet());
    }

    private Set<Stock> getInValidStocks() {
        return STOCKS.stream()
                .filter(s -> !isValid(s.getStockExchange()))
                .collect(Collectors.toSet());
    }

    private boolean isValid(String exchange) {
        return EXCHANGES.stream().filter(e -> e.equals(exchange)).count() > 0;
    }
}
