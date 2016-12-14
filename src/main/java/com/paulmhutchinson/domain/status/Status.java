package com.paulmhutchinson.domain.status;

public enum Status {

    STARTING("Starting..."),
    READING_SYMBOLS("\tReading symbol list..."),
    RETRIEVING_STOCKS("\tRetrieving stocks..."),
    RETRIEVING_RESULT("\tRetrieving result..."),
    FILTERING_STOCKS("\t\tFiltering stocks..."),
    APPLYING_FILTER("\t\t\tApplying {} filter with value(s) {}..."),
    RECOGNIZING_STOCKS("\t\tRecognizing stocks..."),
    RECOGNIZING_PATTERN("\t\t\tRecognizing {} pattern..."),
    WRITING("\tWriting to file..."),
    ERROR_READING_SYMBOLS("\tError reading symbol list"),
    ERROR_RETRIEVING_STOCKS("\tError retrieving stocks"),
    ERROR_RETRIEVING_RESULT("\tError retrieving result"),
    ERROR_FILTERING_STOCKS("\t\tError filtering stocks"),
    ERROR_APPLYING_FILTER("\t\t\tError applying {} filter with value(s) {}"),
    ERROR_WRITING("\tError writing to file"),
    FINISHED("Finished!");

    private String message;

    Status(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
