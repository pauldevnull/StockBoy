package com.paulmhutchinson.domain.stock;

public enum Range {

    ONE_DAY("1d"),
    FIVE_DAY("5d"),
    ONE_MONTH("1m"),
    THREE_MONTHS("3m"),
    SIX_MONTHS("6m"),
    YEAR_TO_DATE("YTD"),
    ONE_YEAR("1y"),
    TWO_YEARS("2y"),
    FIVE_YEARS("5y"),
    TEN_YEARS("10y"),
    MAX("Max");

    private String name;

    Range(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
