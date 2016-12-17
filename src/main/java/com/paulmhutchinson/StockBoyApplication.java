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
    private boolean output;

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
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        YahooFinance.logger.setLevel(Level.OFF);
        SpringApplication.run(StockBoyApplication.class, args).close();
    }
}

/*
    TODO:
    - Weekly spread, monthly spread <= PERCENT temporal spread from average (daily | weekly | monthly) price <= EQUIV TO A VOLATILITY SCORE!
    - trading strategies section of output: limit buy target, limit sell target
    - Growth detection
    - Watchlist
    - Fix PercentChange filters
    - Alerts for FDA approval on new drugs
    - Fibonacci Retracement indicator
    - 'Momentum' variable
    - breakthrough price detector and range detectors
    - good 'setup' (list of conditions met before trade is taken)
    - earnings or FDA decision next day? then sell!
    - spikes/dips in morning on market open?
    - Page 677: Upward Breakouts ****
 */
