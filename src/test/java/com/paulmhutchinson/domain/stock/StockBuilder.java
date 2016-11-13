package com.paulmhutchinson.domain.stock;

import yahoofinance.Stock;
import yahoofinance.quotes.stock.StockQuote;

import java.math.BigDecimal;

public class StockBuilder {

    private String symbol;
    private BigDecimal price;
    private String currency;

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

    public Stock build() {
        Stock stock = new Stock(symbol);
        StockQuote stockQuote = new StockQuote(symbol);
        stockQuote.setPrice(price);
        stock.setCurrency(currency);
        stock.setQuote(stockQuote);
        return stock;
    }
}
