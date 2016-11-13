package com.paulmhutchinson.service.result;

import com.paulmhutchinson.domain.result.Result;
import com.paulmhutchinson.domain.result.ResultBuilder;
import com.paulmhutchinson.domain.status.Status;
import com.paulmhutchinson.service.filter.FilterService;
import com.paulmhutchinson.service.timer.TimerService;
import com.paulmhutchinson.util.filter.FilterUtils;
import com.paulmhutchinson.util.stock.StockUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yahoofinance.Stock;

import java.util.Date;
import java.util.Set;

public class ResultService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultService.class);
    private Set<Stock> stocks = StockUtils.getStocks();
    private FilterService filterService;
    private TimerService timerService;

    public ResultService() {
        this.filterService = new FilterService(stocks, FilterUtils.FILTERS);
        this.timerService = new TimerService();
    }

    public Result getResult() {
        try {
            LOGGER.info(Status.RETRIEVING_RESULT.getMessage());
            Date startTimestamp = new Date();
            timerService.start();
            filterService.filter();
            timerService.stop();
            Date stopTimestamp = new Date();
            return ResultBuilder.aResult()
                    .setStartTimestamp(startTimestamp)
                    .setStopTimestamp(stopTimestamp)
                    .setExecutionTime(timerService.getElapsed())
                    .setResultSize(stocks.size())
                    .setFilters(FilterUtils.FILTERS)
                    .setStocks(stocks)
                    .build();
        } catch (Exception e) {
            LOGGER.error(Status.ERROR_RETRIEVING_RESULT.getMessage());
            return new Result();
        }
    }
}
