package com.paulmhutchinson.domain.result;

import com.paulmhutchinson.domain.filter.Filter;
import yahoofinance.Stock;

import java.util.Date;
import java.util.Set;

public class ResultBuilder {

    private Date startTimestamp;
    private Date stopTimestamp;
    private long executionTime;
    private int resultSize;
    private Set<Filter> filters;
    private Set<Stock> stocks;

    public static ResultBuilder aResult() {
        return new ResultBuilder();
    }

    public ResultBuilder setStartTimestamp(Date startTimestamp) {
        this.startTimestamp = startTimestamp;
        return this;
    }

    public ResultBuilder setStopTimestamp(Date stopTimestamp) {
        this.stopTimestamp = stopTimestamp;
        return this;
    }

    public ResultBuilder setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
        return this;
    }

    public ResultBuilder setResultSize(int resultSize) {
        this.resultSize = resultSize;
        return this;
    }

    public ResultBuilder setFilters(Set<Filter> filters) {
        this.filters = filters;
        return this;
    }

    public ResultBuilder setStocks(Set<Stock> stocks) {
        this.stocks = stocks;
        return this;
    }

    public Result build() {
        return new Result(startTimestamp, stopTimestamp, executionTime, resultSize, filters, stocks);
    }
}
