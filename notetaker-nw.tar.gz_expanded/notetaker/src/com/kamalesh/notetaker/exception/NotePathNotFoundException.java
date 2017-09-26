package com.kamalesh.notetaker.exception;

public class NotePathNotFoundException extends RuntimeException {


    public NotePathNotFoundException(String str) {
        super(str);
    }

    public NotePathNotFoundException(Throwable throwable) {
        super(throwable);
    }
}
