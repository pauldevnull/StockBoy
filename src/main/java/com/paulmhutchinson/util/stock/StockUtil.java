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
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public final class StockUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockUtil.class);
    private static final String CSV_COLUMN = "symbol";
    private static final CSVFormat CSV_FORMAT = CSVFormat.EXCEL.withFirstRecordAsHeader().withIgnoreHeaderCase();

    private StockUtil() {
        throw new AssertionError();
    }

    public static Set<Stock> getStocksForExchange(Exchange exchange) throws IOException {
        Set<String> symbols;
        try {
            LOGGER.info(Status.RETRIEVING_STOCKS.getMessage());
            symbols = getSymbolsForExchange(exchange.getFilename());
            return new HashSet<>(YahooFinance.get(symbols.toArray(new String[symbols.size()])).values());
        } catch (IOException e) {
            LOGGER.error(Status.ERROR_RETRIEVING_STOCKS.getMessage());
            throw new IOException();
        }
    }

    private static Set<String> getSymbolsForExchange(String exchange) throws IOException {
        InputStream inputStream = getInputStream(exchange);
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
