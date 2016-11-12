package com.paulmhutchinson;

import com.paulmhutchinson.domain.result.Result;
import com.paulmhutchinson.domain.status.Status;
import com.paulmhutchinson.service.filewriter.FileWriterService;
import com.paulmhutchinson.service.result.ResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yahoofinance.YahooFinance;

import java.util.logging.Level;

public class StockBoyApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockBoyApplication.class);

    public static void main(String[] args) throws Exception {
        YahooFinance.logger.setLevel(Level.OFF);

        LOGGER.info(Status.STARTING.getMessage());
        ResultService resultService = new ResultService();
        Result result = resultService.getResult();

        FileWriterService fileWriterService = new FileWriterService(result);
        fileWriterService.write();
        LOGGER.info(Status.COMPLETED.getMessage());
    }
}
