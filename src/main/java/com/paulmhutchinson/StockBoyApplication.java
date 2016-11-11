package com.paulmhutchinson;

import com.paulmhutchinson.domain.Constraint;
import com.paulmhutchinson.domain.ConstraintBuilder;
import com.paulmhutchinson.service.StockRetrieverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
@EnableAutoConfiguration
public class StockBoyApplication {

    private static Logger LOGGER = LoggerFactory.getLogger(StockRetrieverService.class);

    public static void main(String[] args) throws Exception {
        StockRetrieverService stockRetrieverService = new StockRetrieverService();
        Constraint constraint = ConstraintBuilder.aConstraint().setMaxPrice(new BigDecimal(10d)).build();

        LOGGER.info("Retrieving stocks...");
        List<Stock> stocks = stockRetrieverService.retrieveStocks(constraint);
        LOGGER.info("Successfully retrieved constrained stocks");

        LOGGER.info("Printing constrained stocks: ");
        System.out.println("\n\n");
        stocks.forEach(s -> System.out.print(s.getSymbol() + ", "));
        System.out.println("\n\n");

        LOGGER.info("Finished and exiting...");
    }
}
