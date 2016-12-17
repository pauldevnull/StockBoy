package com.paulmhutchinson.service.sorter;

import com.paulmhutchinson.domain.sorter.Sorter;
import com.paulmhutchinson.domain.status.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;

import java.util.List;
import java.util.Set;

@Service
public class SorterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SorterService.class);

    private Set<Sorter> sorters;

    @Autowired
    public SorterService(Set<Sorter> sorters) {
        this.sorters = sorters;
    }

    public void sort(List<Stock> stocks) {
        if (!sorters.isEmpty() && !stocks.isEmpty()) {
            LOGGER.info(Status.SORTING_STOCKS.getMessage());
            sorters.forEach(f -> f.sort(stocks));
        }
    }

    public Set<Sorter> getSorters() {
        return sorters;
    }
}
