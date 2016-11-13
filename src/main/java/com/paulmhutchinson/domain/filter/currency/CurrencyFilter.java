package com.paulmhutchinson.domain.filter.currency;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterType;
import com.paulmhutchinson.domain.stock.Currency;
import yahoofinance.Stock;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CurrencyFilter extends Filter {

    private transient Set<Currency> currencies;

    public CurrencyFilter(Set<Currency> currencies) {
        super(FilterType.CURRENCY, currencies.toString());
        this.currencies = currencies;
    }

    @Override
    public Set<Stock> apply(Set<Stock> stocks) {
        try {
            printStatusToLogger();
            return stocks.stream()
                    .filter(s -> isValidCurrency(s.getCurrency()))
                    .collect(Collectors.toSet());
        } catch (Exception e) {
            printErrorToLogger();
            return new HashSet<>();
        }
    }

    private boolean isValidCurrency(final String currency) {
        return currencies.stream().filter(c -> c.toString().equals(currency)).count() > 0;
    }
}
