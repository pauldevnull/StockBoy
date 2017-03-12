package com.paulmhutchinson.domain.filter.price.spread;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterType;
import com.paulmhutchinson.domain.stock.Range;
import com.paulmhutchinson.service.intraday.IntradayService;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;

import java.io.IOException;
import java.math.BigDecimal;

@Component("SpreadFilter")
public abstract class SpreadFilter extends Filter {

    private transient IntradayService intradayService;

    public SpreadFilter() {
        intradayService = new IntradayService();
    }

    public SpreadFilter(FilterType filterType, String filterValue) {
        super(filterType, filterValue);
        intradayService = new IntradayService();
    }

    protected BigDecimal getDailySpread(Stock stock) {
        try {
            return intradayService.getSpreadForRanges(intradayService.getRanges(stock.getSymbol(), Range.ONE_DAY));
        } catch (IOException e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    protected BigDecimal getWeeklySpread(Stock stock) {
        try {
            return intradayService.getSpreadForRanges(intradayService.getRanges(stock.getSymbol(), Range.FIVE_DAY));
        } catch (IOException e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    protected BigDecimal getMonthlySpread(Stock stock) {
        try {
            return intradayService.getSpreadForRanges(intradayService.getRanges(stock.getSymbol(), Range.ONE_MONTH));
        } catch (IOException e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }
}
