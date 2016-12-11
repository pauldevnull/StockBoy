package com.paulmhutchinson.domain.result;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.recognizer.Recognizer;
import yahoofinance.Stock;

import java.util.Set;

public class ResultBuilder {

    private String startTimestamp;
    private String stopTimestamp;
    private long executionTime;
    private int resultSize;
    private Set<Filter> filters;
    private Set<Recognizer> recognizers;
    private Set<String> summary;
    private Set<Stock> stocks;

    public static ResultBuilder aResult() {
        return new ResultBuilder();
    }

    public ResultBuilder setStartTimestamp(String startTimestamp) {
        this.startTimestamp = startTimestamp;
        return this;
    }

    public ResultBuilder setStopTimestamp(String stopTimestamp) {
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

    public ResultBuilder setRecognizers(Set<Recognizer> recognizers) {
        this.recognizers = recognizers;
        return this;
    }

    public ResultBuilder setSummary(Set<String> summary) {
        this.summary = summary;
        return this;
    }

    public ResultBuilder setStocks(Set<Stock> stocks) {
        this.stocks = stocks;
        return this;
    }

    public Result build() {
        return new Result(startTimestamp, stopTimestamp, executionTime, resultSize, filters, recognizers, summary, stocks);
    }
}
