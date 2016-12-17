package com.paulmhutchinson.service.result;

import com.paulmhutchinson.domain.result.Result;
import com.paulmhutchinson.domain.result.ResultBuilder;
import com.paulmhutchinson.service.filter.FilterService;
import com.paulmhutchinson.service.recognizer.RecognizerService;
import com.paulmhutchinson.service.sorter.SorterService;
import com.paulmhutchinson.util.stock.StockUtil;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ResultService {

    private FilterService filterService;
    private RecognizerService recognizerService;
    private SorterService sorterService;

    public ResultService() {}

    @Autowired
    public ResultService(FilterService filterService, RecognizerService recognizerService, SorterService sorterService) {
        this.filterService = filterService;
        this.recognizerService = recognizerService;
        this.sorterService = sorterService;
    }

    public Result getResultFromSymbols(Set<String> symbols) throws IOException {
        List<Stock> stocks = StockUtil.getStocksForSymbols(symbols);
        filterService.filter(stocks);
        recognizerService.recognize(stocks);
        sorterService.sort(stocks);
        long executionTime = ManagementFactory.getRuntimeMXBean().getUptime();
        return ResultBuilder.aResult()
                .setStartTimestamp(DateTime.now().minus(executionTime).toString())
                .setExecutionTime(ManagementFactory.getRuntimeMXBean().getUptime())
                .setResultSize(stocks.size())
                .setFilters(filterService.getFilters())
                .setRecognizers(recognizerService.getRecognizers())
                .setSorters(sorterService.getSorters())
                .setSummary(stocks.stream().map(Stock::toString).collect(Collectors.toList()))
                .setStocks(stocks)
                .build();
    }
}
