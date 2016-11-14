package com.paulmhutchinson.domain.stock;

import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StockFactory {

    private static final List<String> SYMBOLS = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");
    private static final List<String> FILTERED_SYMBOLS = Arrays.asList("C", "D", "E");

    public static Set<Stock> buildDefaultStocks() {
        return new LinkedHashSet<>(IntStream.range(0, SYMBOLS.size())
                .mapToObj(i ->
                        StockBuilder.aStock()
                                .setSymbol(SYMBOLS.get(i))
                                .setPrice(new BigDecimal((i + 1) * 2))
                                .setCurrency(i < 5 ? Currency.USD.toString() : Currency.EUR.toString())
                                .setExchange(i < 5 ? Exchange.DOW.getExchange() : Exchange.NASDAQ.getExchange())
                                .setYearLow(new BigDecimal(20))
                                .setYearHigh(new BigDecimal(20))
                                .build())
                .collect(Collectors.toList()));
    }

    public static Set<Stock> buildDefaultFilteredStocks() {
        return new LinkedHashSet<>(IntStream.range(2, FILTERED_SYMBOLS.size() + 2)
                .mapToObj(i ->
                        StockBuilder.aStock()
                                .setSymbol(FILTERED_SYMBOLS.get(i - 2))
                                .setPrice(new BigDecimal((i + 1) * 2))
                                .setCurrency(i < 5 ? Currency.USD.toString() : Currency.EUR.toString())
                                .setExchange(i < 5 ? Exchange.DOW.getExchange() : Exchange.NASDAQ.getExchange())
                                .setYearLow(new BigDecimal(20))
                                .setYearHigh(new BigDecimal(20))
                                .build())
                .collect(Collectors.toList()));
    }

    public static Set<Stock> getStocksFromSymbols(Set<Stock> stocks, Set<String> symbols) {
        return stocks.stream().filter(s -> symbols.contains(s.getSymbol())).collect(Collectors.toSet());
    }
}
