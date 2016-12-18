package com.paulmhutchinson.domain.sorter.price;

import com.paulmhutchinson.domain.sorter.SorterType;
import com.paulmhutchinson.domain.sorter.Sorter;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;

import javax.swing.*;
import java.util.Collections;
import java.util.List;

@Component("CurrentPriceSorter")
public class CurrentPriceSorter extends Sorter {

    private transient SortOrder sorterOrder;

    public CurrentPriceSorter() {}

    public CurrentPriceSorter(SortOrder sorterOrder) {
        super(SorterType.CURRENT_PRICE, sorterOrder);
        this.sorterOrder = sorterOrder;
    }

    @Override
    public void sort(List<Stock> stocks) {
        printStatusToLogger();
        if (sorterOrder == SortOrder.ASCENDING)
            Collections.sort(stocks, (s1, s2) -> s1.getQuote().getPrice().compareTo(s2.getQuote().getPrice()));

        if (sorterOrder == SortOrder.DESCENDING)
            Collections.sort(stocks, (s1, s2) -> s2.getQuote().getPrice().compareTo(s1.getQuote().getPrice()));
    }
}
