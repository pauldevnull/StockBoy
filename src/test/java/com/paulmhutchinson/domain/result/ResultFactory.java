package com.paulmhutchinson.domain.result;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterFactory;
import com.paulmhutchinson.domain.recognizer.Recognizer;
import com.paulmhutchinson.domain.recognizer.RecognizerFactory;
import com.paulmhutchinson.domain.stock.StockFactory;
import org.joda.time.DateTime;
import yahoofinance.Stock;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ResultFactory {

    private static final DateTime DEFAULT_START_TIMESTAMP = new DateTime(5L);
    private static final DateTime DEFAULT_STOP_TIMESTAMP = new DateTime(10L);
    private static final long DEFAULT_EXECUTION_TIME = 10;
    private static final Set<Recognizer> DEFAULT_RECOGNIZERS = RecognizerFactory.buildDefaultRecognizers();
    private static final Set<Filter> DEFAULT_FILTERS = FilterFactory.buildDefaultFilters();
    private static final Set<String> DEFAULT_SUMMARY = new HashSet<>(Arrays.asList("C", "D", "E"));
    private static final Set<Stock> DEFAULT_STOCKS = StockFactory.buildDefaultFilteredStocks();

    public static Result buildDefaultResult() {
        return ResultBuilder.aResult()
                .setStartTimestamp(DEFAULT_START_TIMESTAMP.toString())
                .setStopTimestamp(DEFAULT_STOP_TIMESTAMP.toString())
                .setExecutionTime(DEFAULT_EXECUTION_TIME)
                .setResultSize(DEFAULT_STOCKS.size())
                .setFilters(DEFAULT_FILTERS)
                .setRecognizers(DEFAULT_RECOGNIZERS)
                .setSummary(DEFAULT_SUMMARY)
                .setStocks(DEFAULT_STOCKS)
                .build();
    }

    public static String buildDefaultResultString() {
        return  "Result{resultSize=3, " +
                "filters=[Filter{filterType=CURRENCY, filterValue='[USD]'}, " +
                         "Filter{filterType=EXCHANGE, filterValue='[^DJI]'}, " +
                         "Filter{filterType=MIN_PRICE, filterValue='5'}, " +
                         "Filter{filterType=MAX_PRICE, filterValue='15'}, " +
                         "Filter{filterType=PERCENT_CHANGE_FROM_YEAR_LOW, filterValue='50'}, " +
                         "Filter{filterType=PERCENT_CHANGE_FROM_YEAR_HIGH, filterValue='50'}], " +
                "recognizers=[], " +
                "summary=[C, D, E], " +
                "stocks=[C: 6, D: 8, E: 10]}";
    }
}
