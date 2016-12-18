package com.paulmhutchinson.domain.filter.spread.minimum.temporal;

import com.paulmhutchinson.domain.filter.FilterType;
import com.paulmhutchinson.domain.filter.spread.minimum.MinSpreadFilter;
import org.apache.commons.collections4.CollectionUtils;
import org.joda.time.DateTimeConstants;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

@Component("WeeklySpreadFilter")
public class MinWeeklySpreadFilter extends MinSpreadFilter {

    private transient BigDecimal minWeeklySpread;

    public MinWeeklySpreadFilter() {}

    public MinWeeklySpreadFilter(String minWeeklySpread) {
        super(FilterType.MIN_WEEKLY_SPREAD, minWeeklySpread);
        this.minWeeklySpread = new BigDecimal(minWeeklySpread);
    }

    @Override
    public void filter(List<Stock> stocks) {
        printStatusToLogger();
        CollectionUtils.filter(stocks, stock -> isGreaterThanMinSpread(getSpread(stock)));
    }

    @Override
    protected BigDecimal getSpread(Stock stock) {
        Calendar from = Calendar.getInstance();
        from.add(Calendar.DAY_OF_YEAR, -DateTimeConstants.DAYS_PER_WEEK);
        try {
            List<HistoricalQuote> historicalQuotes = stock.getHistory(from, Calendar.getInstance(), Interval.DAILY);
            BigDecimal weeklyLow = historicalQuotes.stream().min((q1, q2) -> q1.getLow().compareTo(q2.getLow())).get().getLow();
            BigDecimal weeklyHigh = historicalQuotes.stream().min((q1, q2) -> q1.getHigh().compareTo(q2.getHigh())).get().getHigh();
            return weeklyHigh.subtract(weeklyLow);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    @Override
    protected boolean isGreaterThanMinSpread(BigDecimal weeklySpread) {
        return weeklySpread.compareTo(minWeeklySpread) >= 0;
    }
}
