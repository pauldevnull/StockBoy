package com.paulmhutchinson.service.stock;

import com.paulmhutchinson.domain.status.Status;
import com.paulmhutchinson.util.stock.SymbolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StockService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockService.class);
    private List<String> symbols;

    public StockService() {
        symbols = new SymbolUtil().getSymbols().subList(0, 5000);
    }

    public Set<Stock> getStocks() {
        try {
            LOGGER.info(Status.RETRIEVING_STOCKS.getMessage());
            return new HashSet<>(YahooFinance.get(symbols.toArray(new String[symbols.size()])).values());
        } catch (Exception e) {
            LOGGER.error(Status.ERROR_RETRIEVING_STOCKS.getMessage());
            return new HashSet<>();
        }
    }
}
