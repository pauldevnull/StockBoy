package com.paulmhutchinson.domain.filter.currency;

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
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyFilterTest {

    private static final Set<Currency> CURRENCIES = Collections.singleton(Currency.USD);
    private static final Set<Stock> STOCKS = StockFactory.buildStocks(10);
    private CurrencyFilter currencyFilter;

    @Before
    public void init() {
        currencyFilter = new CurrencyFilter(CURRENCIES);
    }

    @Test
    public void apply_WithListOfStocksAndCurrencies_ExpectOnlyStocksWithValidCurrencies() {
        Set<Stock> filteredStocks = currencyFilter.apply(new HashSet<>(STOCKS));

        assertFalse(CollectionUtils.containsAny(filteredStocks, getInValidStocks()));
        assertTrue(filteredStocks.containsAll(getValidStocks()));
    }

    private Set<Stock> getValidStocks() {
        return STOCKS.stream()
                .filter(s -> isValid(s.getCurrency()))
                .collect(Collectors.toSet());
    }

    private Set<Stock> getInValidStocks() {
        return STOCKS.stream()
                .filter(s -> !isValid(s.getCurrency()))
                .collect(Collectors.toSet());
    }

    private boolean isValid(String currency) {
        return CURRENCIES.stream().filter(c -> c.toString().equals(currency)).count() > 0;
    }
}
