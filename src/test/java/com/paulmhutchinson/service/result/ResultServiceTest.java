package com.paulmhutchinson.service.result;

import com.paulmhutchinson.domain.filter.FilterFactory;
import com.paulmhutchinson.domain.result.Result;
import com.paulmhutchinson.domain.result.ResultFactory;
import com.paulmhutchinson.domain.stock.StockFactory;
import com.paulmhutchinson.service.filter.FilterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ResultServiceTest {

    private static final Result RESULT = ResultFactory.buildDefaultResult();

    private ResultService resultService;

    @Before
    public void init() {
        FilterService filterService = new FilterService(StockFactory.buildDefaultStocks(), FilterFactory.buildDefaultFilters());
        resultService = new ResultService(filterService);
    }

    @Test
    public void getResult_withDefaultValues_expectCorrectResult() {
        Result result = resultService.getResult();

        assertEquals(RESULT.toString(), result.toString());
    }
}
