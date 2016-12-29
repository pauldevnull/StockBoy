package com.paulmhutchinson.domain.filter.currency;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterType;
import com.paulmhutchinson.domain.stock.Currency;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component("CurrencyFilter")
public class CurrencyFilter extends Filter {

    private transient Set<Currency> currencies;

    public CurrencyFilter() {}

    public CurrencyFilter(String currencies) {
        super(FilterType.CURRENCY, currencies);
        this.currencies = Arrays
                .asList(currencies.replace('[', ' ').replace(']', ' ').trim().split(","))
                .stream()
                .map(c -> Currency.valueOf(c.trim()))
                .collect(Collectors.toSet());
    }

    @Override
    public void filter(List<Stock> stocks) {
        printStatusToLogger();
        CollectionUtils.filter(stocks, stock -> isValidCurrency(stock.getCurrency()));
    }

    private boolean isValidCurrency(final String currency) {
        try {
            return currencies.contains(Currency.valueOf(currency));
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
