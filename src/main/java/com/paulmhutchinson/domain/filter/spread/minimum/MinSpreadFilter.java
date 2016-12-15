package com.paulmhutchinson.domain.filter.spread.minimum;

import com.paulmhutchinson.domain.filter.FilterType;
import com.paulmhutchinson.domain.filter.spread.SpreadFilter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("MinSpreadFilter")
public abstract class MinSpreadFilter extends SpreadFilter {

    public transient BigDecimal minSpread;

    public MinSpreadFilter() {}

    public MinSpreadFilter(FilterType filterType, String filterValue) {
        super(filterType, filterValue);
        this.minSpread = new BigDecimal(filterValue);
    }

    protected boolean isGreaterThanMinSpread(BigDecimal spread) {
        return spread.compareTo(minSpread) >= 0;
    }
}
