package com.paulmhutchinson.service.filter;

import com.paulmhutchinson.domain.status.Status;
import com.paulmhutchinson.util.filter.FilterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yahoofinance.Stock;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilterService.class);
    private List<Stock> stocks;

    public FilterService(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public List<Stock> getFilteredStocks() {
        try {
            LOGGER.info(Status.FILTERING_STOCKS.getMessage());
            return FilterUtil.FILTERS.stream()
                    .flatMap(f -> f.apply(stocks).stream())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error(Status.ERROR_FILTERING_STOCKS.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }
}
