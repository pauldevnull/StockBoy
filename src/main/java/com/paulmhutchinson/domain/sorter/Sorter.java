package com.paulmhutchinson.domain.sorter;

import com.google.gson.annotations.SerializedName;
import com.paulmhutchinson.domain.status.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;

import javax.swing.*;
import java.io.Serializable;
import java.util.List;

@Component
public abstract class Sorter implements Serializable {

    private static transient final Logger LOGGER = LoggerFactory.getLogger(Sorter.class);

    @SerializedName("sorterType")
    private SortType sorterType;

    @SerializedName("sorterOrder")
    private SortOrder sorterOrder;

    protected Sorter() {}

    protected Sorter(SortType sorterType, SortOrder sorterOrder) {
        this.sorterType = sorterType;
        this.sorterOrder = sorterOrder;
    }

    @Autowired
    public abstract void sort(List<Stock> stocks);

    protected void printStatusToLogger() {
        LOGGER.info(Status.APPLYING_SORTER.getMessage(), sorterType, sorterOrder);
    }

    @Override
    public String toString() {
        return "Sorter{" +
                "sorterType=" + sorterType +
                ", sorterOrder=" + sorterOrder +
                '}';
    }
}
