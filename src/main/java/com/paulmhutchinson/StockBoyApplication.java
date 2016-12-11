package com.paulmhutchinson;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.input.StockInput;
import com.paulmhutchinson.domain.recognizer.Recognizer;
import com.paulmhutchinson.domain.recognizer.pattern.flag.highandtight.HighAndTightFlag;
import com.paulmhutchinson.domain.status.Status;
import com.paulmhutchinson.domain.stock.Exchange;
import com.paulmhutchinson.service.filewriter.FileWriterService;
import com.paulmhutchinson.service.filter.FilterService;
import com.paulmhutchinson.service.recognizer.RecognizerService;
import com.paulmhutchinson.service.result.ResultService;
import com.paulmhutchinson.util.filewriter.FileWriterUtil;
import com.paulmhutchinson.util.input.InputUtil;
import com.paulmhutchinson.util.stock.StockUtil;
import org.apache.commons.collections4.SetUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StockBoyApplication {

    /*
        TODO:
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

    private static final Logger LOGGER = LoggerFactory.getLogger(StockBoyApplication.class);

    private static Set<Filter> filters;
    private static Set<Recognizer> recognizers;
    private static Set<Stock> stocks;

    public static void main(String[] args) throws Exception {
        YahooFinance.logger.setLevel(Level.OFF);
        LOGGER.info(Status.STARTING.getMessage());

        // Inputs
        processArgs(args);

        // Logic
        FilterService filterService = new FilterService(filters);
        RecognizerService recognizerService = new RecognizerService(recognizers);
        ResultService resultService = new ResultService(filterService, recognizerService);
        FileWriterService fileWriterService = new FileWriterService(FileWriterUtil.FILENAME);

        // Outputs
        fileWriterService.write(resultService.getResult(stocks));
        LOGGER.info(Status.FINISHED.getMessage());
    }

    private static void processArgs(String[] args) throws IOException {
        if (args.length == 0) {
            filters = SetUtils.emptySet();
            recognizers = Stream.of(new HighAndTightFlag()).collect(Collectors.toCollection(HashSet::new));
            stocks = new HashSet<>(StockUtil.getStocksForExchange(Exchange.NASDAQ_PENNY));
        } else {
            StockInput inputs = InputUtil.process(args[0]);
            filters = inputs.getFilters();
            recognizers = inputs.getRecognizers();
            stocks = new HashSet<>(StockUtil.getStocksForSymbols(inputs.getSymbols()));
        }
    }
}
