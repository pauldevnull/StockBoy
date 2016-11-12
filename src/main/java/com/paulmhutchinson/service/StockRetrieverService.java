package com.paulmhutchinson.service;

import com.paulmhutchinson.domain.ConstraintType;
import com.paulmhutchinson.repository.SymbolRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StockRetrieverService {

    private Logger logger = LoggerFactory.getLogger(StockRetrieverService.class);
    private List<String> symbols = SymbolRepository.buildSymbolList();
    private double symbolCount = symbols.size();
    private DecimalFormat df = new DecimalFormat("#0.00");

    public List<Stock> retrieveStocks(Map<ConstraintType, Object> constraints) throws Exception {
        return symbols.stream()
                .filter(s -> meetsConstraints(s, constraints))
                .map(YahooFinance::get)
                .collect(Collectors.toList());
    }

    private boolean meetsConstraints(String symbol, Map<ConstraintType, Object> constraints) {
        logger.info("PROGRESS: {}%", getProgress(symbol));
        return isBelowMaxPrice(symbol, (BigDecimal) constraints.get(ConstraintType.MAX_PRICE)) &&
               isBelowPercentFrom52WeekLow(symbol, (BigDecimal) constraints.get(ConstraintType.PERCENT_FROM_52_WEEK_LOW));
    }

    private boolean isBelowMaxPrice(String symbol, BigDecimal maxPrice) {
        logger.info("\t\tChecking {} constraint against: {}", ConstraintType.MAX_PRICE.toString(), symbol);
        try {
            return YahooFinance.get(symbol).getQuote().getPrice().compareTo(maxPrice) < 0;
        } catch (Exception e) {
            logger.error("Error retrieving symbol: {}", symbol);
            return false;
        }
    }

    private boolean isBelowPercentFrom52WeekLow(String symbol, BigDecimal percentFrom52WeekLow) {
        logger.info("\t\tChecking {} constraint against: {}", ConstraintType.PERCENT_FROM_52_WEEK_LOW.toString(), symbol);
        try {
            return Math.abs(YahooFinance.get(symbol).getQuote().getChangeFromYearLowInPercent().doubleValue()) <= percentFrom52WeekLow.doubleValue();
        } catch (Exception e) {
            logger.error("Error retrieving symbol: {}", symbol);
            return false;
        }
    }

    private String getProgress(String symbol) {
        double index = symbols.indexOf(symbol);
        double progress = index / symbolCount;
        return df.format(progress * 100);
    }
}
