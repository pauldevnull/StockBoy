package com.paulmhutchinson.domain.result;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ResultTest {

    private static final Result DEFAULT_RESULT = ResultFactory.buildDefaultResult();

    @Test
    public void toString_withResult_expectCorrectString() {
        String correctResultString = ResultFactory.buildDefaultResultString();

        String resultString = DEFAULT_RESULT.toString();

        assertEquals(correctResultString, resultString);
    }
}
