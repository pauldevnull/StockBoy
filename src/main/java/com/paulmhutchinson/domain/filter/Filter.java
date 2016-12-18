package com.paulmhutchinson.domain.filter;

import com.google.gson.annotations.SerializedName;
import com.paulmhutchinson.domain.status.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;

import java.io.Serializable;
import java.util.List;

@Component
public abstract class Filter implements Serializable {

    private static transient final Logger LOGGER = LoggerFactory.getLogger(Filter.class);

    @SerializedName("filterType")
    private FilterType filterType;
    @SerializedName("filterValue")
    private String filterValue;

    protected Filter() {}

    protected Filter(FilterType filterType, String filterValue) {
        this.filterType = filterType;
        this.filterValue = filterValue;
    }

    public abstract void filter(List<Stock> stocks);

    protected void printStatusToLogger() {
        LOGGER.info(Status.APPLYING_FILTER.getMessage(), filterType, filterValue);
    }

    public FilterType getFilterType() {
        return filterType;
    }

    public String getFilterValue() {
        return filterValue;
    }

    @Override
    public String toString() {
        return "Filter{" +
                "filterType=" + filterType +
                ", filterValue='" + filterValue + '\'' +
                '}';
    }
}
