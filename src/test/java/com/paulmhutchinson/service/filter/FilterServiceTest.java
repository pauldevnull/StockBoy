
package com.paulmhutchinson.service.filter;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.currency.Currency;
import com.paulmhutchinson.domain.filter.currency.CurrencyFilter;
import com.paulmhutchinson.domain.filter.exchange.Exchange;
import com.paulmhutchinson.domain.filter.exchange.ExchangeFilter;
import com.paulmhutchinson.domain.filter.price.MaxPriceFilter;
import com.paulmhutchinson.domain.filter.price.MinPriceFilter;
import com.paulmhutchinson.domain.stock.StockFactory;
import org.apache.commons.collections4.CollectionUtils;
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
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class FilterServiceTest {

    private static final int STOCK_COUNT = 10;
    private static final Set<Stock> STOCKS = StockFactory.buildStocks(STOCK_COUNT);
    private static final Set<Currency> CURRENCIES = new HashSet<>(Collections.singletonList(Currency.USD));
    private static final Set<String> EXCHANGES = new HashSet<>(Arrays.asList(Exchange.DOW.getExchange(), Exchange.NASDAQ.getExchange(), Exchange.SP.getExchange()));
    private static final BigDecimal MIN_PRICE = new BigDecimal(5);
    private static final BigDecimal MAX_PRICE = new BigDecimal(16);
    private static final Filter CURRENCY_FILTER = new CurrencyFilter(CURRENCIES);
    private static final Filter EXCHANGE_FILTER = new ExchangeFilter(EXCHANGES);
    private static final Filter MIN_PRICE_FILTER = new MinPriceFilter(MIN_PRICE);
    private static final Filter MAX_PRICE_FILTER = new MaxPriceFilter(MAX_PRICE);
    private static final Set<Filter> FILTERS = new HashSet<>(Arrays.asList(
        CURRENCY_FILTER, EXCHANGE_FILTER, MIN_PRICE_FILTER, MAX_PRICE_FILTER
    ));
    private FilterService filterService;

    @Before
    public void init() {
        filterService = new FilterService(STOCKS, FILTERS);
    }

    @Test
    public void getFilteredStocks_withAllFilters_expectOnlyStocksWithinConstraints() {
        Set<Stock> filteredStocks = filterService.getFilteredStocks();

        assertFalse(CollectionUtils.containsAny(filteredStocks, getStocksOutsideConstraints()));
        assertTrue(filteredStocks.containsAll(getStocksWithinConstraints()));
    }

    private Set<Stock> getStocksWithinConstraints() {
        return new HashSet<>(CollectionUtils.intersection(
               new HashSet<>(CollectionUtils.intersection(getStocksWithValidCurrency(),getStocksWithValidExchange())),
               new HashSet<>(CollectionUtils.intersection(getStocksAboveMinPrice(), getStocksBelowMaxPrice())))
        );
    }

    private Set<Stock> getStocksOutsideConstraints() {
        return new HashSet<>(CollectionUtils.union(
               new HashSet<>(CollectionUtils.union(getStocksWithInvalidCurrency(), getStocksWithInvalidExchange())),
               new HashSet<>(CollectionUtils.union(getStocksBelowMinPrice(), getStocksAboveMaxPrice())))
        );
    }

    private Set<Stock> getStocksWithValidCurrency() {
        return CURRENCY_FILTER.apply(STOCKS);
    }

    private Set<Stock> getStocksWithInvalidCurrency() {
        Set<Stock> stocksWithValidCurrency = getStocksWithValidCurrency();
        return STOCKS.stream().filter(stocksWithValidCurrency::contains).collect(Collectors.toSet());
    }

    private Set<Stock> getStocksWithValidExchange() {
        return EXCHANGE_FILTER.apply(STOCKS);
    }

    private Set<Stock> getStocksWithInvalidExchange() {
        Set<Stock> stocksWithValidExchange = getStocksWithValidExchange();
        return STOCKS.stream().filter(stocksWithValidExchange::contains).collect(Collectors.toSet());
    }

    private Set<Stock> getStocksBelowMinPrice() {
        return STOCKS.stream()
                .filter(s -> s.getQuote().getPrice().compareTo(MIN_PRICE) < 0)
                .collect(Collectors.toSet());
    }

    private Set<Stock> getStocksAboveMinPrice() {
        return STOCKS.stream()
                .filter(s -> s.getQuote().getPrice().compareTo(MIN_PRICE) > 0)
                .collect(Collectors.toSet());
    }

    private Set<Stock> getStocksBelowMaxPrice() {
        return STOCKS.stream()
                .filter(s -> s.getQuote().getPrice().compareTo(MAX_PRICE) < 0)
                .collect(Collectors.toSet());
    }

    private Set<Stock> getStocksAboveMaxPrice() {
        return STOCKS.stream()
                .filter(s -> s.getQuote().getPrice().compareTo(MAX_PRICE) > 0)
                .collect(Collectors.toSet());
    }
}