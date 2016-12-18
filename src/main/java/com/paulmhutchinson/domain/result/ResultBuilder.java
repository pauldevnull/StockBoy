package com.paulmhutchinson.domain.result;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.recognizer.Recognizer;
import com.paulmhutchinson.domain.sorter.Sorter;
import yahoofinance.Stock;

import java.util.List;
import java.util.Set;

public class ResultBuilder {

    private String startTimestamp;
    private double executionTimeInMilliseconds;
    private double executionTimeInSeconds;
    private double executionTimeInMinutes;
    private int resultSize;
    private Set<Filter> filters;
    private Set<Recognizer> recognizers;
    private Set<Sorter> sorters;
    private List<String> summary;
    private List<Stock> stocks;

    public static ResultBuilder aResult() {
        return new ResultBuilder();
    }

    public ResultBuilder setStartTimestamp(String startTimestamp) {
        this.startTimestamp = startTimestamp;
        return this;
    }

    public ResultBuilder setExecutionTimeInMilliseconds(double executionTimeInMilliseconds) {
        this.executionTimeInMilliseconds = executionTimeInMilliseconds;
        return this;
    }

    public ResultBuilder setExecutionTimeInSeconds(double executionTimeInSeconds) {
        this.executionTimeInSeconds = executionTimeInSeconds;
        return this;
    }

    public ResultBuilder setExecutionTimeInMinutes(double executionTimeInMinutes) {
        this.executionTimeInMinutes = executionTimeInMinutes;
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

    public ResultBuilder setSorters(Set<Sorter> sorters) {
        this.sorters = sorters;
        return this;
    }

    public ResultBuilder setSummary(List<String> summary) {
        this.summary = summary;
        return this;
    }

    public ResultBuilder setStocks(List<Stock> stocks) {
        this.stocks = stocks;
        return this;
    }

    public Result build() {
        return new Result(startTimestamp, executionTimeInMilliseconds, executionTimeInSeconds, executionTimeInMinutes, resultSize, filters, recognizers, sorters, summary, stocks);
    }
}
