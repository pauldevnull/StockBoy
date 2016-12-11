package com.paulmhutchinson.domain.recognizer.pattern;

import com.paulmhutchinson.domain.stock.VolumeTrend;

public class PatternData {

    private double performanceRank;
    private double breakEvenFailureRate;
    private double averageDecline;
    private double changeAfterTrendEnds;
    private VolumeTrend volumeTrend;
    private double pullbacks;
    private double meetingPriceTarget;

    public PatternData(double performanceRank, double breakEvenFailureRate, double averageDecline, double changeAfterTrendEnds, VolumeTrend volumeTrend, double pullbacks, double meetingPriceTarget) {
        this.performanceRank = performanceRank;
        this.breakEvenFailureRate = breakEvenFailureRate;
        this.averageDecline = averageDecline;
        this.changeAfterTrendEnds = changeAfterTrendEnds;
        this.volumeTrend = volumeTrend;
        this.pullbacks = pullbacks;
        this.meetingPriceTarget = meetingPriceTarget;
    }

    public double getPerformanceRank() {
        return performanceRank;
    }

    public void setPerformanceRank(int performanceRank) {
        this.performanceRank = performanceRank;
    }

    public double getBreakEvenFailureRate() {
        return breakEvenFailureRate;
    }

    public void setBreakEvenFailureRate(double breakEvenFailureRate) {
        this.breakEvenFailureRate = breakEvenFailureRate;
    }

    public double getAverageDecline() {
        return averageDecline;
    }

    public void setAverageDecline(double averageDecline) {
        this.averageDecline = averageDecline;
    }

    public double getChangeAfterTrendEnds() {
        return changeAfterTrendEnds;
    }

    public void setChangeAfterTrendEnds(double changeAfterTrendEnds) {
        this.changeAfterTrendEnds = changeAfterTrendEnds;
    }

    public VolumeTrend getVolumeTrend() {
        return volumeTrend;
    }

    public void setVolumeTrend(VolumeTrend volumeTrend) {
        this.volumeTrend = volumeTrend;
    }

    public double getPullbacks() {
        return pullbacks;
    }

    public void setPullbacks(double pullbacks) {
        this.pullbacks = pullbacks;
    }

    public double getMeetingPriceTarget() {
        return meetingPriceTarget;
    }

    public void setMeetingPriceTarget(double meetingPriceTarget) {
        this.meetingPriceTarget = meetingPriceTarget;
    }
}
