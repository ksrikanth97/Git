package com.kamalesh.notetaker.exception;

public class TooManyArgumentsException extends RuntimeException {

    public TooManyArgumentsException() {
        super("Too many arguments passed.");
    }
}
