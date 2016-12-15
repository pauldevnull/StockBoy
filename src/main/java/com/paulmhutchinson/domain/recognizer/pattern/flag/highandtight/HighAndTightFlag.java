package com.paulmhutchinson.domain.recognizer.pattern.flag.highandtight;

import com.paulmhutchinson.domain.market.MarketType;
import com.paulmhutchinson.domain.recognizer.RecognizerType;
import com.paulmhutchinson.domain.recognizer.pattern.PatternData;
import com.paulmhutchinson.domain.recognizer.pattern.flag.Flag;
import com.paulmhutchinson.domain.status.Status;
import com.paulmhutchinson.domain.stock.VolumeTrend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;
import yahoofinance.histquotes.HistoricalQuote;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
The pattern sports a huge average rise with small failures.
Throwbacks hurt performance.
Short or narrow patterns perform better than tall or wide ones.
The price trend after the flag is about half as far as the trend leading to the flag.
Patterns with a falling volume trend perform well.

...just 5 out of 307 failed to climb at least 10%,
and none failed to climb at least 5%.
That is an excellent start for any chart pattern.

-Characteristics
Substantial rise
A rise lasting less than 2 months carries prices upward at least 90% (shoot for a doubling of the stock price).

Find consolidation
Locate a consolidation area, where prices pause in the prevailing uptrend near where price doubles from the trend start.

Receding volume trend (performance indicator...not identification guide
The volume trend in the flag should be receding for best
performance.
 */

@Component
public class HighAndTightFlag implements Flag, Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(HighAndTightFlag.class);

    private RecognizerType recognizerType;
    private transient Map<MarketType, PatternData> patternData;

    public HighAndTightFlag() {
        this.recognizerType = RecognizerType.HIGH_AND_TIGHT_FLAG;
        this.patternData = buildPatternData();
    }

    public void recognize(Set<Stock> stocks) {
        LOGGER.info(Status.RECOGNIZING_PATTERN.getMessage(), recognizerType.toString());
        stocks.removeIf(this::isNotSubstantialRise);
    }

    @Override
    public String toString() {
        return "HighAndTightFlag{" +
                "recognizerType=" + recognizerType +
                ", patternData=" + patternData +
                '}';
    }

    public Map<MarketType, PatternData> getPatternData() {
        return patternData;
    }

    private boolean isNotSubstantialRise(Stock stock) {
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
    }

    private Map<MarketType, PatternData> buildPatternData() {
        return new HashMap<MarketType, PatternData>() {{
            put(MarketType.BEAR, buildBearMarketPatternData());
            put(MarketType.BULL, buildBullMarketPatternData());
        }};
    }

    private PatternData buildBearMarketPatternData() {
        return new PatternData(
                PERFORMANCE_RANK_BEAR,
                BREAK_EVEN_FAILURE_RATE_BEAR,
                AVERAGE_RISE_BEAR,
                CHANGE_AFTER_TREND_ENDS_BEAR,
                VOLUME_TREND_BEAR,
                THROWBACKS_BEAR,
                MEETING_PRICE_TARGET_BEAR
        );
    }

    private PatternData buildBullMarketPatternData() {
        return new PatternData(
                PERFORMANCE_RANK_BULL,
                BREAK_EVEN_FAILURE_RATE_BULL,
                AVERAGE_RISE_BULL,
                CHANGE_AFTER_TREND_ENDS_BULL,
                VOLUME_TREND_BULL,
                THROWBACKS_BULL,
                MEETING_PRICE_TARGET_BULL
        );
    }

    private static final double PERFORMANCE_RANK_BEAR = 1d/19d;
    private static final double PERFORMANCE_RANK_BULL = 1d/23d;
    private static final double BREAK_EVEN_FAILURE_RATE_BEAR = 0d;
    private static final double BREAK_EVEN_FAILURE_RATE_BULL = 0d;
    private static final double AVERAGE_RISE_BEAR = 0.42d;
    private static final double AVERAGE_RISE_BULL = 0.69d;
    private static final double CHANGE_AFTER_TREND_ENDS_BEAR = -0.35d;
    private static final double CHANGE_AFTER_TREND_ENDS_BULL= -0.36d;
    private static final VolumeTrend VOLUME_TREND_BEAR = VolumeTrend.DOWNWARD;
    private static final VolumeTrend VOLUME_TREND_BULL = VolumeTrend.DOWNWARD;
    private static final double THROWBACKS_BEAR = 0.65d;
    private static final double THROWBACKS_BULL = 0.54d;
    private static final double MEETING_PRICE_TARGET_BEAR = 0.91d;
    private static final double MEETING_PRICE_TARGET_BULL = 0.90d;
}
