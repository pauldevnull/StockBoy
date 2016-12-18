package com.paulmhutchinson.domain.recognizer;

import com.paulmhutchinson.domain.recognizer.pattern.flag.highandtight.HighAndTightFlag;

public enum RecognizerType {

    HIGH_AND_TIGHT_FLAG(HighAndTightFlag.class);

    private Class clazz;

    RecognizerType(Class clazz) {
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }
}
