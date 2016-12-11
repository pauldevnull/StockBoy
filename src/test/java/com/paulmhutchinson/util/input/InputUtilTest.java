package com.paulmhutchinson.util.input;

import com.paulmhutchinson.StockBoyApplicationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InputUtilTest {

    @Test(expected = AssertionError.class)
    public void classWithPrivateCtorTest() throws Throwable {
        StockBoyApplicationTest.invokePrivateConstructor(InputUtil.class);
    }
}
