package com.paulmhutchinson.util.stock;

import com.google.gson.Gson;
import com.paulmhutchinson.domain.status.Status;
import com.paulmhutchinson.domain.stock.Exchange;
import com.paulmhutchinson.util.filewriter.FileWriterUtil;
import org.apache.commons.csv.CSVFormat;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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

    public static List<Stock> getStocksForSymbols(Set<String> symbols, Calendar from) {
        LOGGER.info(Status.RETRIEVING_STOCKS.getMessage());
        return new ArrayList<>(YahooFinance.get(symbols.toArray(new String[symbols.size()]), from, Calendar.getInstance(), Interval.DAILY).values());
    }

    public static List<Stock> getStocksForExchange(Exchange exchange, Calendar from) throws IOException {
        Set<String> symbols = getSymbolsFromFile(exchange.getFilename());
        return getStocksForSymbols(symbols, from);
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
