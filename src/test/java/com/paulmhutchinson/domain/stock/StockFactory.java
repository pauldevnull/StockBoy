package com.paulmhutchinson.domain.stock;

import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StockFactory {

    public static final Set<String> SYMBOLS = new LinkedHashSet<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J"));
    private static final Set<String> FILTERED_SYMBOLS = new HashSet<>(Arrays.asList("C", "D", "E"));

    public static Set<Stock> buildDefaultStocks() {
        List<String> symbols = new ArrayList<>(SYMBOLS);
        return IntStream.range(0, symbols.size())
                .mapToObj(i ->
                        StockBuilder.aStock()
                                .setSymbol(symbols.get(i))
                                .setPrice(new BigDecimal((i + 1) * 2))
                                .setCurrency(i < 5 ? Currency.USD.toString() : Currency.EUR.toString())
                                .setExchange(i < 5 ? Exchange.DOW.getExchange() : Exchange.NASDAQ.getExchange())
                                .setYearLow(new BigDecimal(20))
                                .setYearHigh(new BigDecimal(20))
                                .build())
                .collect(Collectors.toSet());
    }

    public static Set<Stock> buildDefaultFilteredStocks() {
        List<String> filteredSymbols = new ArrayList<>(FILTERED_SYMBOLS);
        return new LinkedHashSet<>(IntStream.range(2, filteredSymbols.size() + 2)
                .mapToObj(i ->
                        StockBuilder.aStock()
                                .setSymbol(filteredSymbols.get(i - 2))
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
