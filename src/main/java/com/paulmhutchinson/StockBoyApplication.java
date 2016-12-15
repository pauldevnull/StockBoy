package com.paulmhutchinson;

import com.paulmhutchinson.domain.status.Status;
import com.paulmhutchinson.service.filewriter.FileWriterService;
import com.paulmhutchinson.service.result.ResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import yahoofinance.YahooFinance;

import javax.annotation.Resource;
import java.util.Set;
import java.util.logging.Level;

/*
    TODO:
    - Weekly spread, monthly spread
    - trading strategies section of output: limit buy target, limit sell target
    - Growth detection
    - Watchlist
    - Fix PercentChange filters
    - Alerts for FDA approval on new drugs
    - Fibonacci Retracement indicator
    - 'Momentum' variable
    - breakthrough price detector and range detectors
    - good 'setup' (list of conditions met before trade is taken)
    - earnings or FDA decision next day? then sell!
    - spikes/dips in morning on market open?
    - Page 677: Upward Breakouts ****
 */

@SpringBootApplication
public class StockBoyApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockBoyApplication.class);

    @Resource
    private Set<String> symbols;

    @Autowired
    private ResultService resultService;

    @Autowired
    private FileWriterService fileWriterService;

    @Override
    public void run(String[] args) throws Exception {
        fileWriterService.write(resultService.getResultFromSymbols(symbols));
        LOGGER.info(Status.FINISHED.getMessage());
    }

    public static void main(String[] args) {
        YahooFinance.logger.setLevel(Level.OFF);
        SpringApplication.run(StockBoyApplication.class, args).close();
    }
}
