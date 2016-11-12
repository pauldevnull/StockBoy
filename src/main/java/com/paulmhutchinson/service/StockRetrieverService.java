package com.paulmhutchinson.service;

import com.google.gson.GsonBuilder;
import com.paulmhutchinson.domain.ConstraintType;
import com.paulmhutchinson.repository.SymbolRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StockRetrieverService {

    private Logger logger;
    private List<String> symbols;
    private int symbolCount;
    private DecimalFormat df;

    public StockRetrieverService() {
        logger = LoggerFactory.getLogger(StockRetrieverService.class);
        df = new DecimalFormat("#0.00");
        symbols = SymbolRepository.buildSymbolList().subList(0, 100);
        symbolCount = symbols.size();
    }

    public void retrieveStocks(Map<ConstraintType, Object> constraints) throws Exception {
        final long startTime = System.currentTimeMillis();
        logger.info("Retrieving stocks...");
        Map<String, Stock> stocks = YahooFinance.get(symbols.toArray(new String[symbolCount]));
        List<Stock> results = constrain(new ArrayList<>(stocks.values()), constraints);
        logger.info("Successfully retrieved constrained stocks");
        final long endTime = System.currentTimeMillis();
        final long executionTime = endTime - startTime;
        writeToFile(results, executionTime);
    }

    private List<Stock> constrain(List<Stock> stocks, Map<ConstraintType, Object> constraints) {
        return stocks.stream()
                .filter(s -> isBelowMaxPrice(s, (BigDecimal) constraints.get(ConstraintType.MAX_PRICE)))
                .filter(s -> isBelowPercentFromOneYearLow(s, (BigDecimal) constraints.get(ConstraintType.PERCENT_FROM_ONE_YEAR_LOW)))
                .collect(Collectors.toList());
        // logger.info("PROGRESS: {}%", getProgress(stock.getSymbol()));
    }

    private boolean isBelowMaxPrice(Stock stock, BigDecimal maxPrice) {
        logger.info("\t\tChecking {} constraint against: {}", ConstraintType.MAX_PRICE.toString(), stock.getSymbol());
        try {
            return stock.getQuote().getPrice().compareTo(maxPrice) < 0;
        } catch (Exception e) {
            logger.error("Error retrieving symbol: {}", stock.getSymbol());
            return false;
        }
    }

    private boolean isAboveMinPrice(Stock stock, BigDecimal maxPrice) {
        logger.info("\t\tChecking {} constraint against: {}", ConstraintType.MAX_PRICE.toString(), stock.getSymbol());
        try {
            return YahooFinance.get(stock.getSymbol()).getQuote().getPrice().compareTo(maxPrice) < 0;
        } catch (Exception e) {
            logger.error("Error retrieving symbol: {}", stock.getSymbol());
            return false;
        }
    }

    private boolean isBelowPercentFromOneYearLow(Stock stock, BigDecimal percentFromOneYearLow) {
        logger.info("\t\tChecking {} constraint against: {}", ConstraintType.PERCENT_FROM_ONE_YEAR_LOW.toString(), stock.getSymbol());
        try {
            return Math.abs(YahooFinance.get(stock.getSymbol()).getQuote().getChangeFromYearLowInPercent().doubleValue()) <= percentFromOneYearLow.doubleValue();
        } catch (Exception e) {
            logger.error("Error retrieving symbol: {}", stock.getSymbol());
            return false;
        }
    }

    private String getProgress(String symbol) {
        double index = symbols.indexOf(symbol);
        double progress = index / (double) symbolCount;
        return df.format(progress * 100);
    }

    private void writeToFile(List<Stock> stocks, long executionTime) {
        try{
            logger.info("Writing results to file...");
            Date date = new Date();
            String filename = "output/" + date.toString() + ".txt";
            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            writer.print("\"executionTime\":" + executionTime + ",\n");
            writer.print(new GsonBuilder()
                    .setPrettyPrinting()
                    .create()
                    .toJson(stocks)
            );
            writer.close();
        } catch (Exception e) {
            logger.error("Error writing to file");
        }
    }
}
