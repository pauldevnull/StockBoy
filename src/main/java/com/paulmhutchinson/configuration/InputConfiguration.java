package com.paulmhutchinson.configuration;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterType;
import com.paulmhutchinson.domain.input.Input;
import com.paulmhutchinson.service.filewriter.FileWriterService;
import com.paulmhutchinson.service.filter.FilterService;
import com.paulmhutchinson.service.recognizer.RecognizerService;
import com.paulmhutchinson.service.result.ResultService;
import com.paulmhutchinson.service.sorter.SorterService;
import com.paulmhutchinson.util.filewriter.FileWriterUtil;
import com.paulmhutchinson.util.input.InputUtil;
import com.paulmhutchinson.util.stock.StockUtil;
import org.joda.time.DateTimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.io.IOException;
import java.util.Calendar;
import java.util.Set;

//@Configuration
public class InputConfiguration {

    /*
    @Value("${input.file}")
    private String file;

    @Bean(name = "input")
    public Input input() throws IOException {
        return InputUtil.process(file);
    }

    @Bean(name = "fileWriterService")
    public FileWriterService fileWriterService() {
        return new FileWriterService(FileWriterUtil.FILENAME);
    }

    @Autowired
    @Bean(name = "output")
    @DependsOn("input")
    public Boolean output(Input input) {
        return input.isOutput();
    }

    @Autowired
    @Bean(name = "symbols")
    @DependsOn("input")
    public Set<String> symbols(Input input) throws IOException {
        Set<String> symbols =  input.getSymbols();
        symbols.addAll(StockUtil.getSymbolsFromFile(InputUtil.SYMBOL_FILE_PREFIX + input.getSymbolFile()));
        return symbols;
    }

    @Autowired
    @Bean(name = "resultService")
    @DependsOn("symbols")
    public ResultService resultService(Input input) {
        return new ResultService(new FilterService(input.getFilters()),
                                 new RecognizerService(input.getRecognizers()),
                                 new SorterService(input.getSorters()));
    }

    @Autowired
    @Bean(name = "historicalStart")
    @DependsOn("input")
    public Calendar historicalStart(Input input) {
        Calendar historicalStart = Calendar.getInstance();
        Set<Filter> filters = input.getFilters();
        boolean month = filters.stream().anyMatch(f -> f.getFilterType() == FilterType.MIN_MONTHLY_SPREAD);
        boolean week = filters.stream().anyMatch(f -> f.getFilterType() == FilterType.MIN_WEEKLY_SPREAD);
        if (month) {
            historicalStart.add(Calendar.MONTH, -1);
        } else if (week) {
            historicalStart.add(Calendar.DAY_OF_YEAR, -DateTimeConstants.DAYS_PER_WEEK);
        }
        return historicalStart;
    }
*/
}
