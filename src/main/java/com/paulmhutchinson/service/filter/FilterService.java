package com.paulmhutchinson.service.filter;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.status.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yahoofinance.Stock;

import java.util.Set;

public class FilterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilterService.class);

    private Set<Filter> filters;

    public FilterService(final Set<Filter> filters) {
        this.filters = filters;
    }

    public void filter(Set<Stock> stocks) {
        if (!filters.isEmpty()) {
            LOGGER.info(Status.FILTERING_STOCKS.getMessage());
            filters.forEach(f -> f.filter(stocks));
        }
    }

    public Set<Filter> getFilters() {
        return filters;
    }
}
