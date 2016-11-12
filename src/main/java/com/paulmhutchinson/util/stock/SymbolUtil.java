package com.paulmhutchinson.util.stock;

import com.paulmhutchinson.domain.status.Status;
import com.paulmhutchinson.util.filewriter.InputStreamUtil;
import org.apache.commons.csv.CSVFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class SymbolUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(SymbolUtil.class);
    private static final String CSV_PATH = "symbols.csv";
    private static final String CSV_COLUMN = "symbol";
    private static final CSVFormat CSV_FORMAT = CSVFormat.EXCEL.withFirstRecordAsHeader().withIgnoreHeaderCase();

    public SymbolUtil() {
    }

    public List<String> getSymbols() {
        InputStream inputStream = getInputStream();
        try(InputStreamReader reader = new InputStreamReader(inputStream)) {
            LOGGER.info(Status.READING_SYMBOLS.getMessage());
            return StreamSupport.stream(CSV_FORMAT.parse(reader).spliterator(), false)
                    .map(c -> c.get(CSV_COLUMN))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error(Status.ERROR_READING_SYMBOLS.getMessage());
            return new ArrayList<>();
        }
    }

    private static InputStream getInputStream() {
        return InputStreamUtil.getClassPathInputStream(CSV_PATH);
    }
}
