package com.paulmhutchinson.service.filewriter;

import com.paulmhutchinson.domain.result.Result;
import com.paulmhutchinson.domain.result.ResultFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class FileWriterServiceTest {

    private static final String FILENAME = "TEST.txt";
    private static final Result RESULT = ResultFactory.buildDefaultResult();

    @Mock
    private FileWriterService fileWriterService;

    @Before
    public void init() {
        fileWriterService = new FileWriterService(RESULT, FILENAME);
    }

    @Test
    public void write_WithResult_FileIsCreated() throws Exception {
        boolean isWritten = fileWriterService.write();
        boolean isDeleted = new File(FILENAME).delete();

        assertTrue(isWritten);
        assertTrue(isDeleted);
    }
}
