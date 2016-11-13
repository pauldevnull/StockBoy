package com.paulmhutchinson.domain.result;

import com.google.gson.annotations.SerializedName;
import com.paulmhutchinson.domain.filter.Filter;
import yahoofinance.Stock;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;

public class Result implements Serializable {

    @SerializedName("executionTime") private long executionTime;
    @SerializedName("resultSize") private int resultSize;
    @SerializedName("type") private Set<Filter> filters;
    @SerializedName("stocks") private Set<Stock> stocks;

    public Result() {
    }

    public Result(final long executionTime,
                  final int resultSize,
                  final Set<Filter> filters,
                  final Set<Stock> stocks) {
        this.executionTime = executionTime;
        this.resultSize = resultSize;
        this.filters = filters;
        this.stocks = stocks;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public int getResultSize() {
        return resultSize;
    }

    public Set<Filter> getFilters() {
        return filters;
    }

    public Set<Stock> getStocks() {
        return stocks;
    }

    @Override
    public String toString() {
        return "results [\n" +
               "\texecutionTime: " + executionTime +
               "\tresultSize: " + resultSize +
               "\ttype: " + Arrays.deepToString(filters.toArray()) +
               "\tstocks: " + Arrays.deepToString(stocks.toArray()) +
               "]";
    }
}
