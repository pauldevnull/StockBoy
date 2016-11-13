package com.paulmhutchinson.domain.result;

import com.google.gson.annotations.SerializedName;
import com.paulmhutchinson.domain.filter.Filter;
import yahoofinance.Stock;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;

public class Result implements Serializable {

    @SerializedName("startTimestamp") private Date startTimestamp;
    @SerializedName("endTimestamp") private Date endTimestamp;
    @SerializedName("executionTime") private long executionTime;
    @SerializedName("resultSize") private int resultSize;
    @SerializedName("type") private Set<Filter> filters;
    @SerializedName("stocks") private Set<Stock> stocks;

    public Result() {
    }

    public Result(final Date startTimestamp,
                  final Date endTimestamp,
                  final long executionTime,
                  final int resultSize,
                  final Set<Filter> filters,
                  final Set<Stock> stocks) {
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
        this.executionTime = executionTime;
        this.resultSize = resultSize;
        this.filters = filters;
        this.stocks = stocks;
    }

    public Date getStartTimestamp() {
        return startTimestamp;
    }

    public Date getEndTimestamp() {
        return endTimestamp;
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
               "\tstartTimestamp: " + startTimestamp +
               "\tendTimestamp: " + endTimestamp +
               "\texecutionTime: " + executionTime +
               "\tresultSize: " + resultSize +
               "\ttype: " + Arrays.deepToString(filters.toArray()) +
               "\tstocks: " + Arrays.deepToString(stocks.toArray()) +
               "]";
    }
}
