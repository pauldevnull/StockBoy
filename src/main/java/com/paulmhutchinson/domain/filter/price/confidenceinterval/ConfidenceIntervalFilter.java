package com.paulmhutchinson.domain.filter.price.confidenceinterval;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterType;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.List;

// default 95th% CI
@Component
public class ConfidenceIntervalFilter extends Filter {

    private BigDecimal priceDelta;

    public ConfidenceIntervalFilter() {}

    public ConfidenceIntervalFilter(String priceDelta) {
        super(FilterType.CONFIDENCE_INTERVAL, priceDelta);
        this.priceDelta = new BigDecimal(priceDelta);
    }

    @Override
    public void filter(List<Stock> stocks) {
        printStatusToLogger();
        CollectionUtils.filter(stocks, stock -> isWithinConfidenceInterval(stock));
    }

    private boolean isWithinConfidenceInterval(final Stock stock) {

        return false;
    }}
