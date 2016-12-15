package com.paulmhutchinson.domain.filter.currency;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterType;
import com.paulmhutchinson.domain.stock.Currency;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;

import java.util.Set;

@Component("CurrencyFilter")
public class CurrencyFilter extends Filter {

    private transient Set<Currency> currencies;

    public CurrencyFilter() {}

    public CurrencyFilter(Set<Currency> currencies) {
        super(FilterType.CURRENCY, currencies.toString());
        this.currencies = currencies;
    }

    @Override
    public void filter(Set<Stock> stocks) {
        printStatusToLogger();
        CollectionUtils.filter(stocks, stock -> isValidCurrency(stock.getCurrency()));
    }

    private boolean isValidCurrency(final String currency) {
        return currencies.contains(Currency.valueOf(currency));
    }

    public Set<Currency> getCurrencies() {
        return currencies;
    }
}
