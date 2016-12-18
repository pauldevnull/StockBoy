package com.paulmhutchinson.domain.result;

import com.google.gson.annotations.SerializedName;
import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.recognizer.Recognizer;
import com.paulmhutchinson.domain.sorter.Sorter;
import yahoofinance.Stock;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class Result implements Serializable {

    @SerializedName("startTimestamp") private String startTimestamp;
    @SerializedName("executionTimeInMilliseconds") private double executionTimeInMilliseconds;
    @SerializedName("executionTimeInSeconds") private double executionTimeInSeconds;
    @SerializedName("executionTimeInMinutes") private double executionTimeInMinutes;
    @SerializedName("resultSize") private int resultSize;
    @SerializedName("filters") private Set<Filter> filters;
    @SerializedName("recognizers") private Set<Recognizer> recognizers;
    @SerializedName("sorters") private Set<Sorter> sorters;
    @SerializedName("summary") private List<String> summary;
    @SerializedName("stocks") private List<Stock> stocks;

    public Result(final String startTimestamp,
                  final double executionTimeInMilliseconds,
                  final double executionTimeInSeconds,
                  final double executionTimeInMinutes,
                  final int resultSize,
                  final Set<Filter> filters,
                  final Set<Recognizer> recognizers,
                  final Set<Sorter> sorters,
                  final List<String> summary,
                  final List<Stock> stocks) {
        this.startTimestamp = startTimestamp;
        this.executionTimeInMilliseconds = executionTimeInMilliseconds;
        this.executionTimeInSeconds = executionTimeInSeconds;
        this.executionTimeInMinutes = executionTimeInMinutes;
        this.resultSize = resultSize;
        this.filters = filters;
        this.recognizers = recognizers;
        this.sorters = sorters;
        this.summary = summary;
        this.stocks = stocks;
    }

    @Override
    public String toString() {
        return "Result{" +
                "resultSize=" + resultSize +
                ", filters=" + filters +
                ", recognizers=" + recognizers +
                ", sorters=" + sorters +
                ", summary=" + summary +
                ", stocks=" + stocks +
                '}';
    }
}
