package com.paulmhutchinson.service.recognizer;

import com.paulmhutchinson.domain.recognizer.Recognizer;
import com.paulmhutchinson.domain.status.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;

import java.util.List;
import java.util.Set;

@Service
public class RecognizerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecognizerService.class);

    private Set<Recognizer> recognizers;

    @Autowired
    public RecognizerService(Set<Recognizer> recognizers) {
        this.recognizers = recognizers;
    }

    public void recognize(List<Stock> stocks) {
        if (!recognizers.isEmpty()) {
            LOGGER.info(Status.RECOGNIZING_STOCKS.getMessage());
            recognizers.forEach(r -> r.recognize(stocks));
        }
    }

    public Set<Recognizer> getRecognizers() {
        return recognizers;
    }
}
