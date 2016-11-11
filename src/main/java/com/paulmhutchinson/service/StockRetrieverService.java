package com.paulmhutchinson.service;

import com.paulmhutchinson.domain.Constraint;
import com.paulmhutchinson.repository.SymbolRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockRetrieverService {

    private Logger logger = LoggerFactory.getLogger(StockRetrieverService.class);

    public List<Stock> retrieveStocks(Constraint constraint) throws Exception {
        List<String> symbols = SymbolRepository.buildSymbolList().subList(0, 100);
        logger.info("Successfully retrieved symbol list");
        BigDecimal maxPrice = constraint.getMaxPrice();
        return symbols.stream()
                .filter(s -> isInRange(s, maxPrice))
                .map(YahooFinance::get)
                .collect(Collectors.toList());
    }

    private boolean isInRange(String symbol, BigDecimal maxPrice) {
        logger.info("Checking constraints against: {}", symbol);
        try {
            return YahooFinance.get(symbol).getQuote().getPrice().compareTo(maxPrice) < 0;
        } catch (Exception e) {
            logger.error("Error retrieving symbol: {}", symbol);
            return false;
        }
    }
}
