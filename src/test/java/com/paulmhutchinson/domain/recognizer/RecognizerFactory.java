package com.paulmhutchinson.domain.recognizer;

import java.util.LinkedHashSet;
import java.util.Set;

public class RecognizerFactory {

    public static Set<Recognizer> buildDefaultRecognizers() {
        return new LinkedHashSet<>();
    }
}
