package com.paulmhutchinson.domain.recognizer;

public enum RecognizerType {

    HIGH_AND_TIGHT_FLAG("pattern.flag.highandtight");

    private String recognizerClass;

    RecognizerType(String recognizerClass) {
        this.recognizerClass = recognizerClass;
    }

    public String getRecognizerClass() {
        return Recognizer.RECOGNIZER_CLASS_PREFIX + recognizerClass;
    }
}
