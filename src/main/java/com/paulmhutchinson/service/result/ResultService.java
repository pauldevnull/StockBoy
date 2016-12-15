package com.paulmhutchinson.service.result;

import com.paulmhutchinson.domain.result.Result;
import com.paulmhutchinson.domain.result.ResultBuilder;
import com.paulmhutchinson.domain.status.Status;
import com.paulmhutchinson.service.filter.FilterService;
import com.paulmhutchinson.service.recognizer.RecognizerService;
import com.paulmhutchinson.util.stock.StockUtil;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ResultService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultService.class);

    private FilterService filterService;
    private RecognizerService recognizerService;

    public ResultService() {
    }

    @Autowired
    public ResultService(FilterService filterService, RecognizerService recognizerService) {
        this.filterService = filterService;
        this.recognizerService = recognizerService;
    }

    public Result getResultFromSymbols(Set<String> symbols) throws IOException {
        LOGGER.info(Status.RETRIEVING_RESULT.getMessage());
        DateTime startTimestamp = DateTime.now();
        Set<Stock> stocks = StockUtil.getStocksForSymbols(symbols);
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
