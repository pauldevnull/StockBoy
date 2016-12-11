package com.paulmhutchinson.domain.recognizer;

import com.paulmhutchinson.domain.recognizer.pattern.flag.highandtight.HighAndTightFlag;
import yahoofinance.Stock;

import java.util.Set;

public interface Recognizer {

    void recognize(Set<Stock> stocks);
    /*
    public static Recognizer getRecognizer(RecognizerType recognizerType) {
        Recognizer recognizer = null;
        if (recognizerType == RecognizerType.HIGH_AND_TIGHT_FLAG) {
            recognizer = new HighAndTightFlag();
        }
        return recognizer;
    }

    abstract void recognize(Set<Stock> stocks);
    */
}
