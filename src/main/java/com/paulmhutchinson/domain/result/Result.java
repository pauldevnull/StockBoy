package com.paulmhutchinson.domain.result;

import com.google.gson.annotations.SerializedName;
import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.recognizer.Recognizer;
import yahoofinance.Stock;

import java.io.Serializable;
import java.util.Set;

public class Result implements Serializable {

    @SerializedName("startTimestamp") private String startTimestamp;
    @SerializedName("stopTimestamp") private String stopTimestamp;
    @SerializedName("executionTimeMilliseconds") private long executionTime;
    @SerializedName("resultSize") private int resultSize;
    @SerializedName("filters") private Set<Filter> filters;
    @SerializedName("recognizers") private Set<Recognizer> recognizers;
    @SerializedName("summary") private Set<String> summary;
    @SerializedName("stocks") private Set<Stock> stocks;

    public Result(final String startTimestamp,
                  final String stopTimestamp,
                  final long executionTime,
                  final int resultSize,
                  final Set<Filter> filters,
                  final Set<Recognizer> recognizers,
                  final Set<String> summary,
                  final Set<Stock> stocks) {
        this.startTimestamp = startTimestamp;
        this.stopTimestamp = stopTimestamp;
        this.executionTime = executionTime;
        this.resultSize = resultSize;
        this.filters = filters;
        this.recognizers = recognizers;
        this.summary = summary;
        this.stocks = stocks;
    }

    @Override
    public String toString() {
        return "Result{" +
                "resultSize=" + resultSize +
                ", filters=" + filters +
                ", recognizers=" + recognizers +
                ", summary=" + summary +
                ", stocks=" + stocks +
                '}';
    }
}
