package com.paulmhutchinson.domain.result;


import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterFactory;
import com.paulmhutchinson.domain.stock.StockFactory;
import org.joda.time.DateTime;
import yahoofinance.Stock;

import java.util.Arrays;
import java.util.Set;

public class ResultFactory {

    private static final DateTime DEFAULT_START_TIMESTAMP = new DateTime(5L);
    private static final DateTime DEFAULT_STOP_TIMESTAMP = new DateTime(10L);
    private static final long DEFAULT_EXECUTION_TIME = 10;
    private static final Set<Filter> DEFAULT_FILTERS = FilterFactory.buildDefaultFilters();
    private static final Set<Stock> DEFAULT_STOCKS = StockFactory.buildDefaultFilteredStocks();

    public static Result buildDefaultResult() {
        return ResultBuilder.aResult()
                .setStartTimestamp(DEFAULT_START_TIMESTAMP.toString())
                .setStopTimestamp(DEFAULT_STOP_TIMESTAMP.toString())
                .setExecutionTime(DEFAULT_EXECUTION_TIME)
                .setResultSize(DEFAULT_STOCKS.size())
                .setFilters(DEFAULT_FILTERS)
                .setStocks(DEFAULT_STOCKS)
                .build();
    }

    public static String buildDefaultResultString() {
        return  "results [\n" +
                "resultSize: " + DEFAULT_STOCKS.size() + "\n" +
                "filters: " + Arrays.deepToString(DEFAULT_FILTERS.toArray()) + "\n" +
                "stocks: " + Arrays.deepToString(DEFAULT_STOCKS.toArray()) + "\n] ";
    }
}
