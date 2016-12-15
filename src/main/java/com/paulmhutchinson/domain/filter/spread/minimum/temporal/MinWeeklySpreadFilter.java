package com.paulmhutchinson.domain.filter.spread.minimum.temporal;

import com.paulmhutchinson.domain.filter.FilterType;
import com.paulmhutchinson.domain.filter.spread.minimum.MinSpreadFilter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;
import yahoofinance.histquotes.HistoricalQuote;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Component("WeeklySpreadFilter")
public class MinWeeklySpreadFilter extends MinSpreadFilter {

    private transient BigDecimal minWeeklySpread;

    public MinWeeklySpreadFilter() {}

    public MinWeeklySpreadFilter(BigDecimal minWeeklySpread) {
        super(FilterType.MIN_WEEKLY_SPREAD, minWeeklySpread.toString());
        this.minWeeklySpread = minWeeklySpread;
    }

    @Override
    public void filter(Set<Stock> stocks) {
        printStatusToLogger();
        CollectionUtils.filter(stocks, stock -> isGreaterThanMinSpread(getSpread(stock)));
    }

    @Override
    protected BigDecimal getSpread(Stock stock) {
        Calendar from = Calendar.getInstance();
        from.add(Calendar.WEEK_OF_YEAR, -1);
        Calendar to = from;
        to.add(Calendar.WEEK_OF_YEAR, 1);
        try {
            List<HistoricalQuote> historicalQuote = stock.getHistory(from, to);
            // get lowest price from week

            // get highest price from week
            // return high - low
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /*

            Calendar from = Calendar.getInstance();
        from.add(Calendar.MONTH, -2);
        Calendar to = from;
        to.add(Calendar.DAY_OF_YEAR, 1);
        try {
            HistoricalQuote historicalQuote = stock.getHistory(from, to).get(0);
            BigDecimal historicalPrice = historicalQuote.getClose();
            BigDecimal currentPrice = stock.getQuote().getPrice();
            BigDecimal targetPrice = historicalPrice.add(historicalPrice.multiply(new BigDecimal(0.9)));
            return currentPrice.compareTo(targetPrice) < 0;
        } catch (Exception e) {
            return false;
        }
     */

    @Override
    protected boolean isGreaterThanMinSpread(BigDecimal weeklySpread) {
        return weeklySpread.compareTo(minWeeklySpread) >= 0;
    }
}
