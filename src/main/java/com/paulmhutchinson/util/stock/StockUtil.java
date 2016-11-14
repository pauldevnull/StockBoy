package com.paulmhutchinson.util.stock;

import com.paulmhutchinson.domain.status.Status;
import com.paulmhutchinson.domain.stock.Exchange;
import com.paulmhutchinson.util.filewriter.InputStreamUtil;
import org.apache.commons.csv.CSVFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StockUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockUtil.class);
    private static final String CSV_COLUMN = "symbol";
    private static final CSVFormat CSV_FORMAT = CSVFormat.EXCEL.withFirstRecordAsHeader().withIgnoreHeaderCase();
    private static final Set<String> SYMBOLS = getSymbolsForExchange(Exchange.WATCHLIST);

    public static final Set<Stock> STOCKS = getStocks();

    private static Set<Stock> getStocks() {
        try {
            LOGGER.info(Status.RETRIEVING_STOCKS.getMessage());
            return new HashSet<>(YahooFinance.get(SYMBOLS.toArray(new String[SYMBOLS.size()])).values());
        } catch (Exception e) {
            LOGGER.error(Status.ERROR_RETRIEVING_STOCKS.getMessage());
            return new HashSet<>();
        }
    }

    private static Set<String> getSymbolsForExchange(Exchange exchange) {
        InputStream inputStream = getInputStream(exchange.getFilename());
        try(InputStreamReader reader = new InputStreamReader(inputStream)) {
            LOGGER.info(Status.READING_SYMBOLS.getMessage());
            return StreamSupport.stream(CSV_FORMAT.parse(reader).spliterator(), false)
                    .map(c -> c.get(CSV_COLUMN))
                    .collect(Collectors.toSet());
        } catch (Exception e) {
            LOGGER.error(Status.ERROR_READING_SYMBOLS.getMessage());
            return new HashSet<>();
        }
    }

    private static Set<String> getSymbolsForExchanges(List<Exchange> exchanges) {
        return exchanges.stream().flatMap(e -> getSymbolsForExchange(e).stream()).collect(Collectors.toSet());
    }

    private static InputStream getInputStream(String path) {
        return InputStreamUtil.getClassPathInputStream(path);
    }
}
