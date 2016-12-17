package com.paulmhutchinson.domain.recognizer.pattern.scallop.descending.upwardbreakout;

import com.paulmhutchinson.domain.market.MarketType;
import com.paulmhutchinson.domain.recognizer.pattern.PatternData;
import com.paulmhutchinson.domain.recognizer.pattern.scallop.descending.DescendingScallop;
import com.paulmhutchinson.domain.stock.VolumeTrend;
import yahoofinance.Stock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
FINDINGS:
- Reversals perform better than continuations.
- The best performing have breakouts near the yearly low.
- Performance improves after a throwback and a gap.
- Tall or wide patterns do well.
- Does best when volume is trending upward and has a dome shape.
 */
public class UpwardBreakoutScallop implements DescendingScallop {

    private Map<MarketType, PatternData> patternData;

    public UpwardBreakoutScallop() {
        this.patternData = buildPatternData();
    }

    @Override
    public void recognize(List<Stock> stocks) {

    }

    public Map<MarketType, PatternData> getPatternData() {
        return patternData;
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

    private static final int PERFORMANCE_RANK_BEAR = 16;
    private static final int PERFORMANCE_RANK_BULL = 22;
    private static final double BREAK_EVEN_FAILURE_RATE_BEAR = 0.2d;
    private static final double BREAK_EVEN_FAILURE_RATE_BULL = 0.22d;
    private static final double AVERAGE_RISE_BEAR = 0.2d;
    private static final double AVERAGE_RISE_BULL = 0.22d;
    //private static final double AVERAGE_DECLINE_BEAR = 0.18d;
    //private static final double AVERAGE_DECLINE_BULL = 0.22d;
    private static final double CHANGE_AFTER_TREND_ENDS_BEAR = -0.36d;
    private static final double CHANGE_AFTER_TREND_ENDS_BULL= -0.32d;
    private static final VolumeTrend VOLUME_TREND_BEAR = VolumeTrend.DOWNWARD;
    private static final VolumeTrend VOLUME_TREND_BULL = VolumeTrend.DOWNWARD;
    private static final double THROWBACKS_BEAR = 0.58d;
    private static final double THROWBACKS_BULL = 0.62d;
    //private static final double PULLBACKS_BEAR = 0d;
    //private static final double PULLBACKS_BULL = 0d;
    private static final double MEETING_PRICE_TARGET_BEAR = 0.33d;
    private static final double MEETING_PRICE_TARGET_BULL = 0.35d;
}
