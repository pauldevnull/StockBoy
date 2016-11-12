package com.paulmhutchinson.domain.filter.filters.currency;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterType;
import yahoofinance.Stock;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CurrencyFilter extends Filter {

    private transient List<String> currencies;

    public CurrencyFilter(List<String> currencies) {
        super(FilterType.CURRENCY.toString(), currencies.toString());
        this.currencies = currencies;
    }

    @Override
    public List<Stock> apply(List<Stock> stocks) {
        try {
            printStatusToLogger();
            return stocks.stream()
                    .filter(s -> isValidCurrency(s.getCurrency()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            printErrorToLogger();
            return new ArrayList<>();
        }
    }

    private boolean isValidCurrency(final String currency) {
        return currencies.contains(currency);
    }
}
