package com.paulmhutchinson;

import com.paulmhutchinson.domain.result.Result;
import com.paulmhutchinson.service.filewriter.FileWriterService;
import com.paulmhutchinson.service.result.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import yahoofinance.YahooFinance;

import javax.annotation.Resource;
import java.util.Set;
import java.util.logging.Level;

@SpringBootApplication
public class StockBoyApplication implements CommandLineRunner {

    @Resource
    private Boolean output;

    @Resource
    private Set<String> symbols;

    @Autowired
    private ResultService resultService;

    @Autowired
    private FileWriterService fileWriterService;

    @Override
    public void run(String[] args) throws Exception {
        try {
            Result result = resultService.getResultFromSymbols(symbols);
            if (output) { fileWriterService.write(result); }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        YahooFinance.logger.setLevel(Level.SEVERE);
        SpringApplication.run(StockBoyApplication.class, args).close();
    }
}
