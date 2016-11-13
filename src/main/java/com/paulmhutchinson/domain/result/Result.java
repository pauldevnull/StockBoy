package com.paulmhutchinson.domain.result;

import com.google.gson.annotations.SerializedName;
import com.paulmhutchinson.domain.filter.Filter;
import org.joda.time.DateTime;
import yahoofinance.Stock;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;

public class Result implements Serializable {

    @SerializedName("startTimestamp") private DateTime startTimestamp;
    @SerializedName("stopTimestamp") private DateTime stopTimestamp;
    @SerializedName("executionTime") private long executionTime;
    @SerializedName("resultSize") private int resultSize;
    @SerializedName("type") private Set<Filter> filters;
    @SerializedName("stocks") private Set<Stock> stocks;

    public Result(final DateTime startTimestamp,
                  final DateTime stopTimestamp,
                  final long executionTime,
                  final int resultSize,
                  final Set<Filter> filters,
                  final Set<Stock> stocks) {
        this.startTimestamp = startTimestamp;
        this.stopTimestamp = stopTimestamp;
        this.executionTime = executionTime;
        this.resultSize = resultSize;
        this.filters = filters;
        this.stocks = stocks;
    }

    @Override
    public String toString() {
        return "results [\n" +
               "\tstartTimestamp: " + startTimestamp +
               "\tstopTimestamp: " + stopTimestamp +
               "\texecutionTime: " + executionTime +
               "\tresultSize: " + resultSize +
               "\ttype: " + Arrays.deepToString(filters.toArray()) +
               "\tstocks: " + Arrays.deepToString(stocks.toArray()) +
               "]";
    }
}
