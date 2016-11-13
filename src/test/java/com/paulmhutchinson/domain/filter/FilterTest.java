package com.paulmhutchinson.domain.filter;

import com.paulmhutchinson.domain.filter.currency.CurrencyFilter;
import com.paulmhutchinson.domain.stock.Currency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class FilterTest {

    private static final CurrencyFilter CURRENCY_FILTER = new CurrencyFilter(new HashSet<>(Collections.singletonList(Currency.USD)));

    @Test
    public void toString_WithFilter_ExpectCorrectString() {
        String correctCurrencyFilterString = "filterType:" + FilterType.CURRENCY + ",\n" + "filterValue: " + CURRENCY_FILTER.getCurrencies();

        String currencyFilterString = CURRENCY_FILTER.toString();

        assertEquals(correctCurrencyFilterString, currencyFilterString);
    }
}
