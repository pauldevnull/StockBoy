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

    public static Set<Stock> buildStocks(int count) {
        return IntStream.range(0, count)
                .mapToObj(i ->
                        StockBuilder.aStock()
                                .setSymbol(SYMBOLS.get(i))
                                .setPrice(new BigDecimal((i + 1) * 2))
                                .setCurrency((i % 2) == 0 ? Currency.USD.toString() : Currency.EUR.toString())
                                .build())
                .collect(Collectors.toSet());
    }

    public static Set<Stock> buildStocksAtOrBelowPrice(BigDecimal price) {
        return IntStream.range(0, price.intValue() / 2)
                .mapToObj(i ->
                        StockBuilder.aStock()
                                .setSymbol(SYMBOLS.get(i))
                                .setPrice(new BigDecimal((i + 1) * 2))
                                .build())
                .collect(Collectors.toSet());
    }

    public static Set<Stock> buildStocksAtOrAbovePrice(BigDecimal price) {
        return IntStream.range((price.intValue() / 2) - 1, price.intValue())
                .mapToObj(i ->
                        StockBuilder.aStock()
                                .setSymbol(SYMBOLS.get(i))
                                .setPrice(new BigDecimal((i + 1) * 2))
                                .build())
                .collect(Collectors.toSet());
    }
}
