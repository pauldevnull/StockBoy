package com.paulmhutchinson.service.result;

import com.paulmhutchinson.domain.result.Result;
import com.paulmhutchinson.domain.result.ResultBuilder;
import com.paulmhutchinson.domain.status.Status;
import com.paulmhutchinson.service.filter.FilterService;
import com.paulmhutchinson.service.recognizer.RecognizerService;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yahoofinance.Stock;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public class ResultService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultService.class);

    private FilterService filterService;
    private RecognizerService recognizerService;

    public ResultService(FilterService filterService, RecognizerService recognizerService) {
        this.filterService = filterService;
        this.recognizerService = recognizerService;
    }

    public Result getResult(Set<Stock> stocks) throws IOException {
        LOGGER.info(Status.RETRIEVING_RESULT.getMessage());
        DateTime startTimestamp = DateTime.now();
        filterService.filter(stocks);
        recognizerService.recognize(stocks);
        DateTime stopTimestamp = DateTime.now();
        return ResultBuilder.aResult()
                .setStartTimestamp(startTimestamp.toString())
                .setStopTimestamp(stopTimestamp.toString())
                .setExecutionTime(new Period(startTimestamp, stopTimestamp).getMillis())
                .setResultSize(stocks.size())
                .setFilters(filterService.getFilters())
                .setRecognizers(recognizerService.getRecognizers())
                .setSummary(stocks.stream().map(Stock::getSymbol).collect(Collectors.toSet()))
                .setStocks(stocks)
                .build();
    }
}
