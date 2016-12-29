package com.paulmhutchinson.util.filewriter;

import com.paulmhutchinson.StockBoyApplicationTest;
import com.paulmhutchinson.domain.stock.Exchange;
import com.paulmhutchinson.util.filter.FilterUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class FileWriterUtilTest {

    private static final String NON_EXISTENT_FILE_PATH = Exchange.class.toString();

    @Test
    public void new_CreateNewFileWriterUtil_ExpectInstanceCreated() {
        assertNotNull(FilterUtil.FILTERS);
    }

    @Test(expected = AssertionError.class)
    public void classWithPrivateCtorTest() throws Throwable {
        StockBoyApplicationTest.invokePrivateConstructor(FileWriterUtil.class);
    }

    @Test//(expected = FileWriterUtil.InputStreamOpeningException.class)
    public void getClassPathInputStream_fileDoesNotExist_ExpectIOException() {
        //FileWriterUtil.getClassPathInputStream(NON_EXISTENT_FILE_PATH);
    }
}
