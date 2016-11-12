package com.paulmhutchinson.service.timer;

public class TimerService {

    private long startTime;
    private long stopTime;

    public TimerService() {
    }

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public void stop() {
        stopTime = System.currentTimeMillis();
    }

    public long getElapsed() {
        return stopTime - startTime;
    }
}
