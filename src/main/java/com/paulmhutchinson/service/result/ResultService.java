package com.paulmhutchinson.service.result;

import com.paulmhutchinson.domain.result.Result;
import com.paulmhutchinson.domain.status.Status;
import com.paulmhutchinson.service.filter.FilterService;
import com.paulmhutchinson.service.stock.StockService;
import com.paulmhutchinson.service.timer.TimerService;
import com.paulmhutchinson.util.filter.FilterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yahoofinance.Stock;

import java.util.List;

public class ResultService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultService.class);
    private FilterService filterService;
    private TimerService timerService;

    public ResultService() {
        StockService stockService = new StockService();
        List<Stock> stocks = stockService.getStocks();
        this.filterService = new FilterService(stocks);
        this.timerService = new TimerService();
    }

    public Result getResult() {
        try {
            LOGGER.info(Status.RETRIEVING_RESULT.getMessage());
            timerService.start();;
            final List<Stock> filteredStocks = filterService.getFilteredStocks();
            timerService.stop();
            return new Result(timerService.getElapsed(), filteredStocks.size(), FilterUtil.FILTERS, filteredStocks);
        } catch (Exception e) {
            LOGGER.error(Status.ERROR_RETRIEVING_RESULT.getMessage());
            return new Result();
        }
    }
}
