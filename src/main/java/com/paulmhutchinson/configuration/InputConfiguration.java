package com.paulmhutchinson.configuration;

import com.paulmhutchinson.domain.input.StockInput;
import com.paulmhutchinson.service.filewriter.FileWriterService;
import com.paulmhutchinson.service.filter.FilterService;
import com.paulmhutchinson.service.recognizer.RecognizerService;
import com.paulmhutchinson.service.result.ResultService;
import com.paulmhutchinson.service.sorter.SorterService;
import com.paulmhutchinson.util.filewriter.FileWriterUtil;
import com.paulmhutchinson.util.input.InputUtil;
import com.paulmhutchinson.util.stock.StockUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.io.IOException;
import java.util.Set;

@Configuration
public class InputConfiguration {

    @Value("${input.file}")
    private String file;

    @Bean(name = "stockInput")
    public StockInput stockInput() throws IOException {
        return InputUtil.process(file);
    }

    @Bean(name = "fileWriterService")
    public FileWriterService fileWriterService() {
        return new FileWriterService(FileWriterUtil.FILENAME);
    }

    @Autowired
    @Bean(name = "output")
    @DependsOn("stockInput")
    public Boolean output(StockInput input) {
        return input.isOutput();
    }

    @Autowired
    @Bean(name = "symbols")
    @DependsOn("stockInput")
    public Set<String> symbols(StockInput input) throws IOException {
        Set<String> symbols =  input.getSymbols();
        symbols.addAll(StockUtil.getSymbolsFromFile(InputUtil.SYMBOL_FILE_PREFIX + input.getSymbolFile()));
        return symbols;
    }

    @Autowired
    @Bean(name = "resultService")
    @DependsOn("symbols")
    public ResultService resultService(StockInput input) {
        return new ResultService(new FilterService(input.getFilters()),
                                 new RecognizerService(input.getRecognizers()),
                                 new SorterService(input.getSorters()));
    }
}
