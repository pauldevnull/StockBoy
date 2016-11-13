package com.paulmhutchinson.domain.stock;

import yahoofinance.Stock;
import yahoofinance.quotes.stock.StockQuote;

import java.math.BigDecimal;

public class StockBuilder {

    private String symbol;
    private BigDecimal price;
    private String currency;
    private String exchange;
    private BigDecimal yearHigh;
    private BigDecimal yearLow;

    public static StockBuilder aStock() {
        return new StockBuilder();
    }

    public StockBuilder setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public StockBuilder setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public StockBuilder setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public StockBuilder setExchange(String exchange) {
        this.exchange = exchange;
        return this;
    }

    public StockBuilder setYearHigh(BigDecimal yearHigh) {
        this.yearHigh = yearHigh;
        return this;
    }

    public StockBuilder setYearLow(BigDecimal yearLow) {
        this.yearLow = yearLow;
        return this;
    }

    public Stock build() {
        Stock stock = new Stock(symbol);
        StockQuote stockQuote = new StockQuote(symbol);
        stockQuote.setPrice(price);
        stockQuote.setYearLow(yearLow);
        stockQuote.setYearHigh(yearHigh);
        stock.setCurrency(currency);
        stock.setStockExchange(exchange);
        stock.setQuote(stockQuote);
        return stock;
    }
}
