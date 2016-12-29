package com.paulmhutchinson.service.filter;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.status.Status;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service("FilterService")
public class FilterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilterService.class);

    private Set<Filter> filters;

    @Autowired
    public FilterService(Set<Filter> filters) {
        this.filters = filters;
    }

    public void filter(List<Stock> stocks) {
        if (!filters.isEmpty() && !stocks.isEmpty()) {
            LOGGER.info(Status.FILTERING_STOCKS.getMessage());
            filterDeadStocks(stocks);
            filters.forEach(f -> f.filter(stocks));
        }
    }

    private void filterDeadStocks(List<Stock> stocks) {
        CollectionUtils.filter(stocks, stock -> stock.getQuote().getPrice().compareTo(BigDecimal.ZERO) > 0);
    }

    public Set<Filter> getFilters() {
        return filters;
    }
}
