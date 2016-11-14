package com.paulmhutchinson.domain.status;

public enum Status {

    STARTING("\t\t\tStarting..."),
    READING_SYMBOLS("\t\t\t\tReading symbol list..."),
    RETRIEVING_STOCKS("\t\t\t\tRetrieving stocks..."),
    RETRIEVING_RESULT("\t\tRetrieving result..."),
    FILTERING_STOCKS("\t\t\tFiltering stocks..."),
    APPLYING_FILTER("\t\t\t\t\t\tApplying {} filter with value(s) {}..."),
    WRITING("\t\tWriting to file..."),
    ERROR_READING_SYMBOLS("\t\tError reading symbol list"),
    ERROR_RETRIEVING_STOCKS("\t\tError retrieving stocks"),
    ERROR_RETRIEVING_RESULT("\t\tError retrieving result"),
    ERROR_FILTERING_STOCKS("\t\tError filtering stocks"),
    ERROR_APPLYING_FILTER("\t\t\t\tError applying {} filter with value(s) {}"),
    ERROR_WRITING("\t\tError writing to file"),
    FINISHED("\t\t\tFinished!");

    private String message;

    Status(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
