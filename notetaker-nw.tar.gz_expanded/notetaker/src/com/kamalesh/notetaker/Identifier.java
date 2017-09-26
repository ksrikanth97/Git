package com.kamalesh.notetaker;

public class Identifier {

    private String identifierStr;
    private IdentifierType type;

    public Identifier(String identifierStr, IdentifierType type) {
        this.identifierStr = identifierStr;
        this.type = type;
    }

    public String getIdentifierString() {
        return this.identifierStr;
    }

    public String getIdentifierType() {
        return this.type.name();
    }

    public String getIdentifierSymbol() { return this.type.symbolValue; }

    public boolean isMention() {
        return type != IdentifierType.REFERENCE;
    }

    public boolean isKeyword() {
        return type == IdentifierType.UNIQUE_ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Identifier)) return false;

        Identifier that = (Identifier) o;

        if (!identifierStr.equals(that.identifierStr)) return false;
        return type == that.type;

    }

    @Override
    public int hashCode() {
        int result = identifierStr.hashCode();
        result = 31 * result + type.name().hashCode();
        return result;
    }
}
