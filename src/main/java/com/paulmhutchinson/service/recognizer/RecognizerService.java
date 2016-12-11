package com.paulmhutchinson.service.recognizer;

import com.paulmhutchinson.domain.recognizer.Recognizer;
import com.paulmhutchinson.domain.status.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yahoofinance.Stock;

import java.util.Set;

public class RecognizerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecognizerService.class);

    private Set<Recognizer> recognizers;

    public RecognizerService(final Set<Recognizer> recognizers) {
        this.recognizers = recognizers;
    }

    public void recognize(Set<Stock> stocks) {
        if (!recognizers.isEmpty()) {
            LOGGER.info(Status.RECOGNIZING_STOCKS.getMessage());
            recognizers.forEach(r -> r.recognize(stocks));
        }
    }

    public Set<Recognizer> getRecognizers() {
        return recognizers;
    }
}
