package com.paulmhutchinson.domain.filter.exchange;

import com.paulmhutchinson.domain.stock.Exchange;
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
    private Set<Stock> stocks = StockFactory.buildStocks(10);
    private ExchangeFilter exchangeFilter;

    @Before
    public void init() {
        exchangeFilter = new ExchangeFilter(EXCHANGES);
    }

    @Test
    public void apply_WithListOfStocksAndExchanges_ExpectOnlyStocksWithValidExchanges() {
        exchangeFilter.apply(stocks);

        assertFalse(CollectionUtils.containsAny(stocks, getInValidStocks()));
        assertTrue(stocks.containsAll(getValidStocks()));
    }

    private Set<Stock> getValidStocks() {
        Set<Stock> stocks = this.stocks;
        CollectionUtils.filter(stocks, stock -> isValid(stock.getStockExchange()));
        return stocks;
    }

    private Set<Stock> getInValidStocks() {
        return stocks.stream()
                .filter(s -> !isValid(s.getStockExchange()))
                .collect(Collectors.toSet());
    }

    private boolean isValid(String exchange) {
        return EXCHANGES.stream().filter(e -> e.equals(exchange)).count() > 0;
    }
}
