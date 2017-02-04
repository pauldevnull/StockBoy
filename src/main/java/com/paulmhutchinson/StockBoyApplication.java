package com.paulmhutchinson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.logging.Level;



// Variable: ask for or sort by...average price change (high - low) over past week (maybe include frequency) as a ration of the current price

/*
    DO MIN/MAX DAILY BOUNCE (5 cent intervals) COUNT
    ... days with interval toggle (bc we can't count intra-day data...only daily intervals)

    - filter only stocks that have gone UP in price over the past year
*/






@SpringBootApplication
public class StockBoyApplication  extends SpringBootServletInitializer {

/*
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
            if (output) { fileWriterService.write(result); }
        } catch (Exception e) {
            throw new ErrorProcessingResultException(e.getMessage());
        }
    }
*/

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        YahooFinance.logger.setLevel(Level.OFF);
        return application.sources(StockBoyApplication.class);
    }

    public static void main(String[] args) throws IOException {
        YahooFinance.logger.setLevel(Level.OFF);
        SpringApplication.run(StockBoyApplication.class, args);
    }

    private static final class ErrorProcessingResultException extends RuntimeException {
        public ErrorProcessingResultException(String message) {
            super(message);
        }
    }
}








// calculate percentage of days in year where the stock closed above/below the open price