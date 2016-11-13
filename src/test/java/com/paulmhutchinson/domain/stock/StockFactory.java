package com.paulmhutchinson.domain.stock;

import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StockFactory {

    private static final List<String> SYMBOLS = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");

    public static Set<Stock> buildStocks() {
        return IntStream.range(0, 10)
                .mapToObj(i ->
                        StockBuilder.aStock()
                                .setSymbol(SYMBOLS.get(i))
                                .setPrice(new BigDecimal((i + 1) * 2))
                                .setCurrency(i < 5 ? Currency.USD.toString() : Currency.EUR.toString())
                                .setExchange(i < 5 ? Exchange.DOW.getExchange() : Exchange.NASDAQ.getExchange())
                                .setYearLow(new BigDecimal(20))
                                .setYearHigh(new BigDecimal(20))
                                .build())
                .collect(Collectors.toSet());
    }

    public static Set<Stock> getStocksFromSymbols(Set<Stock> stocks, Set<String> symbols) {
        return stocks.stream().filter(s -> symbols.contains(s.getSymbol())).collect(Collectors.toSet());
    }
}
