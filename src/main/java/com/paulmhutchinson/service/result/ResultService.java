package com.paulmhutchinson.service.result;

import com.paulmhutchinson.domain.result.Result;
import com.paulmhutchinson.domain.result.ResultBuilder;
import com.paulmhutchinson.domain.status.Status;
import com.paulmhutchinson.service.filter.FilterService;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yahoofinance.Stock;

import java.util.Set;

public class ResultService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultService.class);

    private FilterService filterService;

    public ResultService(FilterService filterService) {
        this.filterService = filterService;
    }

    public Result getResult() {
        LOGGER.info(Status.RETRIEVING_RESULT.getMessage());
        DateTime startTimestamp = DateTime.now();
        filterService.filter();
        DateTime stopTimestamp = DateTime.now();
        Set<Stock> stocks = filterService.getStocks();
        return ResultBuilder.aResult()
                .setStartTimestamp(startTimestamp.toString())
                .setStopTimestamp(stopTimestamp.toString())
                .setExecutionTime(new Period(startTimestamp, stopTimestamp).getMillis())
                .setResultSize(stocks.size())
                .setFilters(filterService.getFilters())
                .setStocks(stocks)
                .build();
    }
}
