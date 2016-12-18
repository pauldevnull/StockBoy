package com.paulmhutchinson.domain.filter.spread;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterType;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;
import yahoofinance.quotes.stock.StockQuote;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

@Component("SpreadFilter")
public abstract class SpreadFilter extends Filter {

    public SpreadFilter() {}

    public SpreadFilter(FilterType filterType, String filterValue) {
        super(filterType, filterValue);
    }

    protected BigDecimal getDailySpread(Stock stock) {
        StockQuote quote = stock.getQuote();
        return quote.getDayHigh().subtract(quote.getDayLow());
    }

    protected BigDecimal getWeeklySpread(Stock stock) {
        return getSpread(stock.getHistory(DateTime.now().minusWeeks(1).toCalendar(Locale.ENGLISH), Calendar.getInstance(), Interval.DAILY));
    }

    protected BigDecimal getMonthlySpread(Stock stock) {
        return getSpread(stock.getHistory(DateTime.now().minusMonths(1).toCalendar(Locale.ENGLISH), Calendar.getInstance(), Interval.DAILY));
    }

    private BigDecimal getSpread(List<HistoricalQuote> historicalQuotes) {
        try {
            BigDecimal low = historicalQuotes.stream().min((q1, q2) -> q1.getLow().compareTo(q2.getLow())).get().getLow();
            BigDecimal high = historicalQuotes.stream().min((q1, q2) -> q1.getHigh().compareTo(q2.getHigh())).get().getHigh();
            return high.subtract(low);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }
}
