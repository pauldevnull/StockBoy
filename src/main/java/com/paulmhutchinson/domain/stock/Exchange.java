package com.paulmhutchinson.domain.stock;

public enum Exchange {

    NASDAQ("^IXIC", "nasdaq_symbols.csv"),
    SP("^GSPC", "sp_symbols.csv"),
    DOW("^DJI", "dow_symbols.csv");

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
}
