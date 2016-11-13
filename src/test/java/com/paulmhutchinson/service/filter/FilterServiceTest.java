
package com.paulmhutchinson.service.filter;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.filters.price.MaxPriceFilter;
import com.paulmhutchinson.domain.filter.filters.price.MinPriceFilter;
import com.paulmhutchinson.domain.stock.StockFactory;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class FilterServiceTest {

    private static final BigDecimal MIN_PRICE = new BigDecimal(5);
    private static final BigDecimal MAX_PRICE = new BigDecimal(16);
    private static final BigDecimal MEDIAN_PRICE = new BigDecimal(10);
    private static final Set<Stock> STOCKS_AROUND_PRICE = StockFactory.buildStocksAroundPrice(MEDIAN_PRICE);
    private static final Set<Filter> FILTERS = new HashSet<>(Arrays.asList(
            new MinPriceFilter(MIN_PRICE),
            new MaxPriceFilter(MAX_PRICE)
    ));
    private FilterService filterService;

    @Before
    public void init() {
        filterService = new FilterService(STOCKS_AROUND_PRICE, FILTERS);
    }

    @Test
    public void getFilteredStocks_withMinPriceFilterAndMaxPriceFilter_expectOnlyStocksWithinRange() {
        Set<Stock> filteredStocks = filterService.getFilteredStocks();

        assertFalse(CollectionUtils.containsAny(filteredStocks, getStocksOutsideConstraints()));
        assertTrue(filteredStocks.containsAll(getStocksWithinConstraints()));
    }

    private Set<Stock> getStocksWithinConstraints() {
        return new HashSet<>(CollectionUtils.intersection(getStocksAboveMinPrice(), getStocksBelowMaxPrice()));
    }

    private Set<Stock> getStocksOutsideConstraints() {
        return new HashSet<>(CollectionUtils.union(getStocksBelowMinPrice(), getStocksAboveMaxPrice()));
    }

    private Set<Stock> getStocksBelowMinPrice() {
        return STOCKS_AROUND_PRICE.stream()
                .filter(s -> s.getQuote().getPrice().compareTo(MIN_PRICE) < 0)
                .collect(Collectors.toSet());
    }

    private Set<Stock> getStocksAboveMinPrice() {
        return STOCKS_AROUND_PRICE.stream()
                .filter(s -> s.getQuote().getPrice().compareTo(MIN_PRICE) > 0)
                .collect(Collectors.toSet());
    }

    private Set<Stock> getStocksBelowMaxPrice() {
        return STOCKS_AROUND_PRICE.stream()
                .filter(s -> s.getQuote().getPrice().compareTo(MAX_PRICE) < 0)
                .collect(Collectors.toSet());
    }

    private Set<Stock> getStocksAboveMaxPrice() {
        return STOCKS_AROUND_PRICE.stream()
                .filter(s -> s.getQuote().getPrice().compareTo(MAX_PRICE) > 0)
                .collect(Collectors.toSet());
    }
}