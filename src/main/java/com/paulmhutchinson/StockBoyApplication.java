package com.paulmhutchinson;

import com.paulmhutchinson.domain.ConstraintType;
import com.paulmhutchinson.service.StockRetrieverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@EnableAutoConfiguration
public class StockBoyApplication {

    private static Logger LOGGER = LoggerFactory.getLogger(StockRetrieverService.class);

    public static void main(String[] args) throws Exception {
        StockRetrieverService stockRetrieverService = new StockRetrieverService();

        Map<ConstraintType, Object> constraints = new HashMap<ConstraintType, Object>() {{
            put(ConstraintType.MAX_PRICE, new BigDecimal(10));
            put(ConstraintType.PERCENT_FROM_52_WEEK_LOW, new BigDecimal(5));
        }};

        LOGGER.info("Retrieving stocks...");
        List<Stock> stocks = stockRetrieverService.retrieveStocks(constraints);
        LOGGER.info("Successfully retrieved constrained stocks");

        LOGGER.info("Printing constrained stocks: ");
        System.out.println("\n\n");
        stocks.forEach(s -> System.out.println("SYMBOL: " + s.getSymbol() + ", PRICE: " + s.getQuote().getPrice() + ", CHANGE_FROM_YEAR_LOW_IN_PERCENT: " + s.getQuote().getChangeFromYearLowInPercent()));
        //stocks.forEach(Stock::print);
        System.out.println("\n\n");

        LOGGER.info("Finished and exiting...");
    }
}
