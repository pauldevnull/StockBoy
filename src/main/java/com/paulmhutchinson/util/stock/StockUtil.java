package com.paulmhutchinson.util.stock;

import com.paulmhutchinson.domain.status.Status;
import com.paulmhutchinson.domain.stock.Exchange;
import com.paulmhutchinson.util.filewriter.FileWriterUtil;
import org.apache.commons.csv.CSVFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public final class StockUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockUtil.class);
    private static final String CSV_COLUMN = "symbol";
    private static final CSVFormat CSV_FORMAT = CSVFormat.EXCEL.withFirstRecordAsHeader().withIgnoreHeaderCase();

    private StockUtil() {
        throw new AssertionError();
    }

    public static List<Stock> getStocksForSymbols(Set<String> symbols) {
        LOGGER.info(Status.RETRIEVING_STOCKS.getMessage());
        return new ArrayList<>(YahooFinance.get(symbols.toArray(new String[symbols.size()])).values());
    }

    public static List<Stock> getStocksForExchange(Exchange exchange) throws IOException {
        Set<String> symbols = getSymbolsFromFile(exchange.getFilename());
        return getStocksForSymbols(symbols);
    }

    public static List<Stock> getStocksFromFile(String filename) throws IOException {
        Set<String> symbols = getSymbolsFromFile(filename);
        return getStocksForSymbols(symbols);
    }

    public static Set<String> getSymbolsFromFile(String filename) throws IOException {
        InputStream inputStream = getInputStream(filename);
        InputStreamReader reader = new InputStreamReader(inputStream);
        LOGGER.info(Status.READING_SYMBOLS.getMessage());
        return StreamSupport.stream(CSV_FORMAT.parse(reader).spliterator(), false)
                .map(c -> c.get(CSV_COLUMN))
                .collect(Collectors.toSet());
    }

    private static InputStream getInputStream(String path) {
        return FileWriterUtil.getClassPathInputStream(path);
    }
}
