package com.paulmhutchinson.domain.stock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ExchangeTest {

    private static final Exchange DEFAULT_EXCHANGE = Exchange.DOW;
    private static final String DEFAULT_EXCHANGE_VALUE = "^DJI";
    private static final String DEFAULT_EXCHANGE_FILENAME = "dow_symbols.csv";
    private static final Set<String> DEFAULT_EXCHANGE_VALUES = new LinkedHashSet<>(Arrays.asList("^GSPC", "^IXIC", "^DJI"));

    @Test
    public void getExchange_withExchange_expectCorrectExchange() {
        assertEquals(DEFAULT_EXCHANGE_VALUE, DEFAULT_EXCHANGE.getExchange());
    }

    @Test
    public void getFilename_withExchange_expectCorrectFilename() {
        assertEquals(DEFAULT_EXCHANGE_FILENAME, DEFAULT_EXCHANGE.getFilename());
    }

    @Test
    public void getExchanges_expectAllExchangeValues() {
        assertEquals(DEFAULT_EXCHANGE_VALUES, Exchange.getExchanges());
    }
}
