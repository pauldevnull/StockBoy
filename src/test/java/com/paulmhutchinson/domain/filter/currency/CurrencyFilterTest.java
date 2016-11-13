package com.paulmhutchinson.domain.filter.currency;

import com.paulmhutchinson.domain.stock.Currency;
import com.paulmhutchinson.domain.stock.StockFactory;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import yahoofinance.Stock;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyFilterTest {

    private static final Set<Currency> CURRENCIES = Collections.singleton(Currency.USD);
    private Set<Stock> stocks = StockFactory.buildStocks(10);
    private CurrencyFilter currencyFilter;

    @Before
    public void init() {
        currencyFilter = new CurrencyFilter(CURRENCIES);
    }

    @Test
    public void apply_WithListOfStocksAndCurrencies_ExpectOnlyStocksWithValidCurrencies() {
        currencyFilter.apply(stocks);

        assertFalse(CollectionUtils.containsAny(stocks, getInValidStocks()));
        assertTrue(stocks.containsAll(getValidStocks()));
    }

    private Set<Stock> getValidStocks() {
        Set<Stock> stocks = this.stocks;
        CollectionUtils.filter(stocks, stock -> isValid(stock.getCurrency()));
        return stocks;
    }

    private Set<Stock> getInValidStocks() {
        Set<Stock> stocks = this.stocks;
        CollectionUtils.filter(stocks, stock -> !isValid(stock.getCurrency()));
        return stocks;
    }

    private boolean isValid(String currency) {
        return CURRENCIES.stream().filter(c -> c.toString().equals(currency)).count() > 0;
    }
}
