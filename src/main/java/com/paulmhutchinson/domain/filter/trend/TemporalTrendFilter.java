package com.paulmhutchinson.domain.filter.trend;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterType;
import org.apache.commons.collections4.CollectionUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;
import yahoofinance.histquotes.Interval;
import yahoofinance.quotes.stock.StockQuote;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

@Component("TemporalTrendFilter")
public class TemporalTrendFilter extends Filter {

    private transient TemporalWindow window;
    private transient Trend trend;

    public TemporalTrendFilter() {}

    public TemporalTrendFilter(TemporalWindow window, Trend trend) {
        super(FilterType.TEMPORAL_TREND, window.name() + " " + trend.name());
        this.window = window;
        this.trend = trend;
    }

    @Override
    public void filter(List<Stock> stocks) {
        printStatusToLogger();
        CollectionUtils.filter(stocks, this::isTrending);
    }

    private boolean isTrending(Stock stock) {
        StockQuote quote = stock.getQuote();
        BigDecimal currentPrice = quote.getPrice();

        if (window == TemporalWindow.DAILY) {
            BigDecimal openPrice = quote.getOpen();
            if (trend == Trend.FLAT) return currentPrice.compareTo(openPrice) == 0;
            return trend == Trend.POSTIVE ? currentPrice.compareTo(openPrice) > 0 : currentPrice.compareTo(openPrice) <= 0;
        } else if (window == TemporalWindow.WEEKLY) {
            BigDecimal weekOpenPrice = stock.getHistory(Interval.WEEKLY).get(0).getOpen();
            if (trend == Trend.FLAT) return currentPrice.compareTo(weekOpenPrice) == 0;
            return trend == Trend.POSTIVE ? currentPrice.compareTo(weekOpenPrice) > 0 : currentPrice.compareTo(weekOpenPrice) <= 0;
        } else if (window == TemporalWindow.MONTHLY) {
            BigDecimal monthOpenPrice = stock.getHistory(Interval.MONTHLY).get(0).getOpen();
            if (trend == Trend.FLAT) return currentPrice.compareTo(monthOpenPrice) == 0;
            return trend == Trend.POSTIVE ? currentPrice.compareTo(monthOpenPrice) > 0 : currentPrice.compareTo(monthOpenPrice) <= 0;
        } else if (window == TemporalWindow.YEARLY) {
            BigDecimal yearOpenPrice = stock.getHistory(DateTime.now().minusYears(1).toCalendar(Locale.US)).get(0).getOpen();
            if (trend == Trend.FLAT) return currentPrice.compareTo(yearOpenPrice) == 0;
            return trend == Trend.POSTIVE ? currentPrice.compareTo(yearOpenPrice) > 0 : currentPrice.compareTo(yearOpenPrice) <= 0;
        }
        return false;
    }
}
