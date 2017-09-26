package com.kamalesh.notetaker;

public enum IdentifierType {

    INDIVIDUAL("@"),
    TOPIC("#"),
    UNIQUE_ID("!"),
    REFERENCE("^"),
    URL("&");

    String symbolValue;

    IdentifierType(String symbolValue) {
        this.symbolValue = symbolValue;
    }
}
