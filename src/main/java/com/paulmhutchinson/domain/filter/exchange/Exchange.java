package com.paulmhutchinson.domain.filter.exchange;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public enum  Exchange {

    NASDAQ("^IXIC"),
    SP("^GSPC"),
    DOW("^DJI");

    private String exchange;

    Exchange(String exchange) {
        this.exchange = exchange;
    }

    public String getExchange() {
        return exchange;
    }

    public static Set<String> getExchanges() {
        return new HashSet<>(
                Arrays.asList(Exchange.values())
                        .stream()
                        .map(Exchange::getExchange)
                        .collect(Collectors.toSet()));
    }
}
