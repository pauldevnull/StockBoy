
package com.paulmhutchinson.service.filter;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.currency.CurrencyFilter;
import com.paulmhutchinson.domain.filter.exchange.ExchangeFilter;
import com.paulmhutchinson.domain.filter.price.MaxPriceFilter;
import com.paulmhutchinson.domain.filter.price.MinPriceFilter;
import com.paulmhutchinson.domain.stock.Currency;
import com.paulmhutchinson.domain.stock.Exchange;
import com.paulmhutchinson.domain.stock.StockFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class FilterServiceTest {

    private static final Set<Currency> CURRENCIES = new HashSet<>(Collections.singletonList(Currency.USD));
    private static final Set<String> EXCHANGES = new HashSet<>(Arrays.asList(Exchange.DOW.getExchange(), Exchange.NASDAQ.getExchange(), Exchange.SP.getExchange()));
    private static final BigDecimal MIN_PRICE = new BigDecimal(5);
    private static final BigDecimal MAX_PRICE = new BigDecimal(16);
    private static final Set<String> VALID_SYMBOLS = new HashSet<>(Arrays.asList("E", "C", "D"));

    private Set<Stock> stocks = StockFactory.buildDefaultStocks();
    private FilterService filterService;

    @Before
    public void init() {
        Set<Filter> filters = new HashSet<>(Arrays.asList(
                new CurrencyFilter(CURRENCIES),
                new ExchangeFilter(EXCHANGES),
                new MinPriceFilter(MIN_PRICE),
                new MaxPriceFilter(MAX_PRICE)
        ));
        filterService = new FilterService(stocks, filters);
    }

    @Test
    public void getFilteredStocks_withAllFilters_expectOnlyStocksWithinConstraints() {
        Set<Stock> validStocks = StockFactory.getStocksFromSymbols(stocks, VALID_SYMBOLS);

        filterService.filter();

        assertTrue(stocks.equals(validStocks));
    }
}
