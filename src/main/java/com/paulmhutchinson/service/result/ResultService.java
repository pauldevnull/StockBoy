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

import java.util.Set;

public class ResultService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultService.class);
    private StockService stockService;
    private Set<Stock> stocks;
    private FilterService filterService;
    private TimerService timerService;

    public ResultService() {
        this.stockService = new StockService();
        stocks = stockService.getStocks();
        this.filterService = new FilterService(stocks, FilterUtil.FILTERS);
        this.timerService = new TimerService();
    }

    public Result getResult() {
        try {
            LOGGER.info(Status.RETRIEVING_RESULT.getMessage());
            timerService.start();;
            filterService.filterStocks();
            timerService.stop();
            return new Result(timerService.getElapsed(), stocks.size(), FilterUtil.FILTERS, stocks);
        } catch (Exception e) {
            LOGGER.error(Status.ERROR_RETRIEVING_RESULT.getMessage());
            return new Result();
        }
    }
}
