package com.paulmhutchinson.util.filter;

import com.paulmhutchinson.StockBoyApplicationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class FilterUtilTest {

    @Test
    public void new_CreateNewFileWriterUtil_ExpectInstanceCreated() {
        assertNotNull(FilterUtil.FILTERS);
    }

    @Test(expected = AssertionError.class)
    public void classWithPrivateCtorTest() throws Throwable {
        StockBoyApplicationTest.invokePrivateConstructor(FilterUtil.class);
    }
}
