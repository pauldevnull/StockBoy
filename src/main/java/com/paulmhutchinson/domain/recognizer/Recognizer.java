package com.paulmhutchinson.domain.recognizer;

import org.springframework.stereotype.Component;
import yahoofinance.Stock;

import java.util.List;

@Component("Recognizer")
public interface Recognizer {
    void recognize(List<Stock> stocks);
}
