package com.paulmhutchinson;

import com.paulmhutchinson.domain.result.Result;
import com.paulmhutchinson.service.filewriter.FileWriterService;
import com.paulmhutchinson.service.result.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import yahoofinance.YahooFinance;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Calendar;
import java.util.Set;
import java.util.logging.Level;

@SpringBootApplication
public class StockBoyApplication implements CommandLineRunner {

    @Resource
    private Boolean output;
    @Resource
    private Set<String> symbols;
    @Autowired
    private Calendar historicalStart;
    @Autowired
    private ResultService resultService;
    @Autowired
    private FileWriterService fileWriterService;

    @Override
    public void run(String[] args) throws Exception {
        try {
            Result result = resultService.getResultFromSymbols(symbols, historicalStart);
            if (output) fileWriterService.write(result);
        } catch (Exception e) {
            //e.printStackTrace();
            throw new ErrorProcessingResultException(e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        YahooFinance.logger.setLevel(Level.OFF);
        SpringApplication.run(StockBoyApplication.class, args).close();
    }

    private final class ErrorProcessingResultException extends RuntimeException {
        ErrorProcessingResultException(String message) {
            super(message);
        }
    }
}

/*

- The following ideas would best be done in a single "deep analysis" run that aggregates all this information for a given list of stocks

-- Probability that stock will drop x amount in first y hours of day (i.e. 20 cents in first 2 hours of any given day)

-- X% Confidence Interval that the stock will increase by Y cents the following day
---- based on...

    DO MIN/MAX DAILY BOUNCE (5 cent intervals) COUNT
    ... days with interval toggle (bc we can't count intra-day data...only daily intervals)

    - filter only stocks that have gone UP in price over the past year
*/

// calculate percentage of days in year where the stock closed above/below the open price
