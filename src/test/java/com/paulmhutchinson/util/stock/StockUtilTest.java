package com.paulmhutchinson.util.stock;

import com.paulmhutchinson.StockBoyApplicationTest;
import com.paulmhutchinson.domain.stock.Exchange;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StockUtilTest {

    @Spy
    private Exchange exchange = Exchange.WATCHLIST;

    @Test(expected = AssertionError.class)
    public void classWithPrivateCtorTest() throws Throwable {
        StockBoyApplicationTest.invokePrivateConstructor(StockUtil.class);
    }

    @Test
    public void new_CreateNewFileWriterUtil_ExpectInstanceCreated() throws IOException {
        assertNotNull(StockUtil.getStocksForExchange(exchange));
    }

    @Test(expected = IOException.class)
    public void new2_CreateNewFileWriterUtil_ExpectInstanceCreated() throws IOException {
        when(exchange.getFilename()).thenThrow(IOException.class);

        StockUtil.getStocksForExchange(exchange);
    }
}
