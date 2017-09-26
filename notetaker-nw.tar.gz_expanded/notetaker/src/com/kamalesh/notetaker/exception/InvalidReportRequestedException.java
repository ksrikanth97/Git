package com.kamalesh.notetaker.exception;

public class InvalidReportRequestedException extends RuntimeException {

    public InvalidReportRequestedException() {
        super("Invalid report requested");
    }
}
