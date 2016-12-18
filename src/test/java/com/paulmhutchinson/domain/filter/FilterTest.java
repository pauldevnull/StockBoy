package com.paulmhutchinson.domain.filter;

import com.paulmhutchinson.domain.filter.currency.CurrencyFilter;
import com.paulmhutchinson.domain.stock.Currency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FilterTest {

    private static final CurrencyFilter CURRENCY_FILTER = new CurrencyFilter(Currency.USD.name());

    @Test
    public void toString_WithFilter_ExpectCorrectString() {
        //String correctCurrencyFilterString = "Filter{filterType=" + FilterType.CURRENCY + ", filterValue='" + CURRENCY_FILTER.getCurrencies() + "'}";

        //String currencyFilterString = CURRENCY_FILTER.toString();

        //assertEquals(correctCurrencyFilterString, currencyFilterString);
    }
}
