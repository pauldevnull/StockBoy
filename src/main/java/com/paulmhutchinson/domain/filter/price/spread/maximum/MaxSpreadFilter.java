package com.paulmhutchinson.domain.filter.price.spread.maximum;

import com.paulmhutchinson.domain.filter.FilterType;
import com.paulmhutchinson.domain.filter.price.spread.SpreadFilter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("MaxSpreadFilter")
public abstract class MaxSpreadFilter extends SpreadFilter {

    public transient BigDecimal maxSpread;

    public MaxSpreadFilter() {}

    public MaxSpreadFilter(FilterType filterType, String filterValue) {
        super(filterType, filterValue);
        this.maxSpread = new BigDecimal(filterValue);
    }

    protected boolean isLessThanMaxSpread(BigDecimal spread) {
        return spread.compareTo(maxSpread) <= 0;
    }
}
