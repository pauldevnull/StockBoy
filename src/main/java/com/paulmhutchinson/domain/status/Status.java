package com.paulmhutchinson.domain.status;

public enum Status {

    READING_SYMBOLS("\t\t\tReading symbol list..."),
    RETRIEVING_STOCKS("\t\tRetrieving stocks..."),
    RETRIEVING_RESULT("\tRetrieving result..."),
    FILTERING_STOCKS("\t\tFiltering stocks..."),
    APPLYING_FILTER("\t\t\t\t\tApplying {} filter with value(s) {}..."),
    WRITING("\tWriting to file..."),
    ERROR_READING_SYMBOLS("\tError reading symbol list"),
    ERROR_RETRIEVING_STOCKS("\tError retrieving stocks"),
    ERROR_RETRIEVING_RESULT("\tError retrieving result"),
    ERROR_FILTERING_STOCKS("\tError filtering stocks"),
    ERROR_APPLYING_FILTER("\t\t\tError applying {} filter with value(s) {}"),
    ERROR_WRITING("\tError writing to file");

    private String message;

    Status(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
