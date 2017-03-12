package com.paulmhutchinson.service.deepanalysis;

import com.paulmhutchinson.service.intraday.IntradayService;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;

import java.util.List;

/*
-- Probability that stock will drop x amount in first y hours of day (i.e. 20 cents in first 2 hours of any given day)

-- X% Confidence Interval that the stock will increase by Y cents the following day
---- based on...
 */


@Service
public class DeepAnalysisService {

    private IntradayService intradayService;

    public DeepAnalysisService() {
        intradayService = new IntradayService();
    }

    public void deepAnalyze(List<Stock> stocks) {
        // go through every single available day of historic data
        //// look at the difference from open price to price after 2 hours
        ////

    }
}
