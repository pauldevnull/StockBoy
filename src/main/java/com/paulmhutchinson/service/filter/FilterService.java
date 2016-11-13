package com.paulmhutchinson.service.filter;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.status.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yahoofinance.Stock;

import java.util.Set;

public class FilterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilterService.class);
    private Set<Stock> stocks;
    private Set<Filter> filters;

    public FilterService(Set<Stock> stocks, final Set<Filter> filters) {
        this.stocks = stocks;
        this.filters = filters;
    }

    public void filterStocks() {
        try {
            LOGGER.info(Status.FILTERING_STOCKS.getMessage());
            filters.forEach(f -> f.apply(stocks));
        } catch (Exception e) {
            LOGGER.error(Status.ERROR_FILTERING_STOCKS.getMessage());
        }
    }

    public Set<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(Set<Stock> stocks) {
        this.stocks = stocks;
    }
}
