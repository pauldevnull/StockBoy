package com.paulmhutchinson.domain.result;


import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterFactory;
import com.paulmhutchinson.domain.stock.StockFactory;
import org.joda.time.DateTime;
import yahoofinance.Stock;

import java.util.Set;

public class ResultFactory {

    private static final DateTime DEFAULT_START_TIMESTAMP = new DateTime(5L);
    private static final DateTime DEFAULT_STOP_TIMESTAMP = new DateTime(10L);
    private static final long DEFAULT_EXECUTION_TIME = 10;
    private static final int DEFAULT_RESULT_SIZE = 5;
    private static final Set<Filter> DEFAULT_FILTERS = FilterFactory.buildDefaultFilters();
    private static final Set<Stock> DEFAULT_STOCKS = StockFactory.buildDefaultStocks();

    public static Result buildDefaultResult() {
        return ResultBuilder.aResult()
                .setStartTimestamp(DEFAULT_START_TIMESTAMP)
                .setStopTimestamp(DEFAULT_STOP_TIMESTAMP)
                .setExecutionTime(DEFAULT_EXECUTION_TIME)
                .setResultSize(DEFAULT_RESULT_SIZE)
                .setFilters(DEFAULT_FILTERS)
                .setStocks(DEFAULT_STOCKS)
                .build();
    }

    public static String buildDefaultResultString() {
        return "results [\n" +
                "\tstartTimestamp: 1969-12-31T18:00:00.005-06:00\tstopTimestamp: 1969-12-31T18:00:00.010-06:00\texecutionTime: 10\tresultSize: 5\ttype: [filterType:CURRENCY,\n" +
                "filterValue: [USD], filterType:EXCHANGE,\n" +
                "filterValue: [^DJI], filterType:MIN_PRICE,\n" +
                "filterValue: 5, filterType:MAX_PRICE,\n" +
                "filterValue: 15, filterType:PERCENT_CHANGE_FROM_YEAR_LOW,\n" +
                "filterValue: 50, filterType:PERCENT_CHANGE_FROM_YEAR_HIGH,\n" +
                "filterValue: 50]\tstocks: [A: 2, B: 4, C: 6, D: 8, E: 10, F: 12, G: 14, H: 16, I: 18, J: 20]]";
    }
}
