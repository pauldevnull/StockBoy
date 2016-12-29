package com.paulmhutchinson.service.result;

import com.paulmhutchinson.domain.result.Result;
import com.paulmhutchinson.domain.result.ResultBuilder;
import com.paulmhutchinson.service.filter.FilterService;
import com.paulmhutchinson.service.recognizer.RecognizerService;
import com.paulmhutchinson.service.sorter.SorterService;
import com.paulmhutchinson.util.stock.StockUtil;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ResultService {

    private static final double MILLIS_PER_SECOND = DateTimeConstants.MILLIS_PER_SECOND;
    private static final double MILLIS_PER_MINUTE = DateTimeConstants.MILLIS_PER_MINUTE;

    private FilterService filterService;
    private RecognizerService recognizerService;
    private SorterService sorterService;

    public ResultService() {}

    @Autowired
    public ResultService(FilterService filterService,
                         RecognizerService recognizerService,
                         SorterService sorterService) {
        this.filterService = filterService;
        this.recognizerService = recognizerService;
        this.sorterService = sorterService;
    }

    public Result getResultFromSymbols(Set<String> symbols, Calendar historicalStart) throws IOException {
        List<Stock> stocks = StockUtil.getStocksForSymbols(symbols, historicalStart);
        filterService.filter(stocks);
        recognizerService.recognize(stocks);
        sorterService.sort(stocks);
        double executionTimeInMilliseconds = ManagementFactory.getRuntimeMXBean().getUptime();
        return ResultBuilder.aResult()
                .setStartTimestamp(DateTime.now().minusMillis((int) executionTimeInMilliseconds).toString())
                .setExecutionTimeInMilliseconds(executionTimeInMilliseconds)
                .setExecutionTimeInSeconds(executionTimeInMilliseconds / MILLIS_PER_SECOND)
                .setExecutionTimeInMinutes(executionTimeInMilliseconds / MILLIS_PER_MINUTE)
                .setResultSize(stocks.size())
                .setSummary(stocks.stream().map(Stock::toString).collect(Collectors.toList()))
                .setStocks(stocks)
                .setFilters(filterService.getFilters())
                .setRecognizers(recognizerService.getRecognizers())
                .setSorters(sorterService.getSorters())
                .build();
    }
}
