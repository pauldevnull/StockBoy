package com.paulmhutchinson.domain.recognizer;

import com.paulmhutchinson.domain.recognizer.pattern.flag.highandtight.HighAndTightFlag;

public enum RecognizerType {

    HIGH_AND_TIGHT_FLAG(HighAndTightFlag.class.getName());

    private String clazz;

    RecognizerType(String clazz) {
        this.clazz = clazz;
    }

    public String getClazz() {
        return clazz;
    }
}
