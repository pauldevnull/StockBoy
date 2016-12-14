package com.paulmhutchinson;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.input.StockInput;
import com.paulmhutchinson.domain.recognizer.Recognizer;
import com.paulmhutchinson.domain.status.Status;
import com.paulmhutchinson.service.filewriter.FileWriterService;
import com.paulmhutchinson.service.filter.FilterService;
import com.paulmhutchinson.service.recognizer.RecognizerService;
import com.paulmhutchinson.service.result.ResultService;
import com.paulmhutchinson.util.filewriter.FileWriterUtil;
import com.paulmhutchinson.util.input.InputUtil;
import com.paulmhutchinson.util.stock.StockUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

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

    @Override
    public void run(String[] args) throws Exception {
        YahooFinance.logger.setLevel(Level.OFF);
        LOGGER.info("\n");
        LOGGER.info(Status.STARTING.getMessage());

        StockInput inputs = InputUtil.process(args[0]);
        Set<Filter> filters = inputs.getFilters();
        Set<Recognizer> recognizers = inputs.getRecognizers();
        Set<Stock> stocks = StockUtil.getStocksForSymbols(inputs.getSymbols());
        stocks.addAll(StockUtil.getStocksFromFile(inputs.getSymbolFile()));

        ResultService resultService = new ResultService(new FilterService(filters), new RecognizerService(recognizers));
        FileWriterService fileWriterService = new FileWriterService(FileWriterUtil.FILENAME);
        fileWriterService.write(resultService.getResult(stocks));

        LOGGER.info(Status.FINISHED.getMessage());
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(StockBoyApplication.class, args).close();
    }
}
