package com.paulmhutchinson;

import com.paulmhutchinson.domain.result.Result;
import com.paulmhutchinson.domain.status.Status;
import com.paulmhutchinson.domain.stock.Exchange;
import com.paulmhutchinson.service.filewriter.FileWriterService;
import com.paulmhutchinson.service.filter.FilterService;
import com.paulmhutchinson.service.result.ResultService;
import com.paulmhutchinson.util.filewriter.FileWriterUtil;
import com.paulmhutchinson.util.filter.FilterUtil;
import com.paulmhutchinson.util.stock.StockUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yahoofinance.YahooFinance;

import java.util.logging.Level;

public class StockBoyApplication {

    /*
        TODO:
        - Growth detection
        - Watchlist
        - Fix PercentChange filters

     */

    private static final Logger LOGGER = LoggerFactory.getLogger(StockBoyApplication.class);

    public static void main(String[] args) throws Exception {
        YahooFinance.logger.setLevel(Level.SEVERE);

        LOGGER.info(Status.STARTING.getMessage());
        FilterService filterService = new FilterService(StockUtil.getStocksForExchange(Exchange.WATCHLIST), FilterUtil.FILTERS);
        ResultService resultService = new ResultService(filterService);
        Result result = resultService.getResult();

        FileWriterService fileWriterService = new FileWriterService(result, FileWriterUtil.FILENAME);
        fileWriterService.write();
        LOGGER.info(Status.FINISHED.getMessage());
    }
}
