package com.paulmhutchinson.domain.sorter.price;

import com.paulmhutchinson.domain.sorter.SortType;
import com.paulmhutchinson.domain.sorter.Sorter;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;

import javax.swing.*;
import java.util.Collections;
import java.util.List;

@Component("CurrentPriceSorter")
public class CurrentPriceSorter extends Sorter {

    private transient SortOrder sortOrder;

    public CurrentPriceSorter() {}

    public CurrentPriceSorter(SortOrder sortOrder) {
        super(SortType.CURRENT_PRICE, sortOrder);
        this.sortOrder = sortOrder;
    }

    @Override
    public void sort(List<Stock> stocks) {
        printStatusToLogger();
        if (sortOrder == SortOrder.ASCENDING)
            Collections.sort(stocks, (s1, s2) -> s1.getQuote().getPrice().compareTo(s2.getQuote().getPrice()));

        if (sortOrder == SortOrder.DESCENDING)
            Collections.sort(stocks, (s1, s2) -> s2.getQuote().getPrice().compareTo(s1.getQuote().getPrice()));
    }
}
