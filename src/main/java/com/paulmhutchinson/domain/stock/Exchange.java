package com.paulmhutchinson.domain.stock;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public enum Exchange {

    NASDAQ("^IXIC", "nasdaq_symbols.csv"),
    NASDAQ_PENNY("^IXIC", "nasdaq_penny_symbols.csv"),
    NYSE("NYSE", "nyse_symbols.csv"),
    SP("^GSPC", "sp_symbols.csv"),
    DOW("^DJI", "dow_symbols.csv"),
    WATCHLIST("WATCHLIST", "watchlist.csv");

    private String exchange;
    private String filename;

    Exchange(String exchange, String filename) {
        this.exchange = exchange;
        this.filename = filename;
    }

    public String getExchange() {
        return exchange;
    }

    public String getFilename() {
        return filename;
    }

    public static Set<String> getExchanges() {
        return new LinkedHashSet<>(
                Arrays.asList(Exchange.values())
                        .stream()
                        .map(Exchange::getExchange)
                        .collect(Collectors.toSet()));
    }
}
