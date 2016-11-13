package com.paulmhutchinson.domain.filter.filters.currency;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterType;
import org.apache.commons.collections4.CollectionUtils;
import yahoofinance.Stock;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CurrencyFilter extends Filter {

    private transient List<String> currencies;

    public CurrencyFilter(List<String> currencies) {
        super(FilterType.CURRENCY.toString(), currencies.toString());
        this.currencies = currencies;
    }

    @Override
    public Set<Stock> apply(Set<Stock> stocks) {
        try {
            printStatusToLogger();
            Set<Stock> filtered = stocks.stream()
                    .filter(s -> isValidCurrency(s.getCurrency()))
                    .collect(Collectors.toSet());
            return new HashSet<>(CollectionUtils.intersection(stocks, filtered));
        } catch (Exception e) {
            printErrorToLogger();
            return new HashSet<>();
        }
    }

    private boolean isValidCurrency(final String currency) {
        return currencies.contains(currency);
    }
}
