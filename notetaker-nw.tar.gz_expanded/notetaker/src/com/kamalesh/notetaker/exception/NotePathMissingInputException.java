package com.kamalesh.notetaker.exception;

public class NotePathMissingInputException extends RuntimeException {
    public NotePathMissingInputException() {
        super("Notepath is not input");
    }
}
