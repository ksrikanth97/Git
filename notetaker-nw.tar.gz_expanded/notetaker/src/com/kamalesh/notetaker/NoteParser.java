package com.kamalesh.notetaker;

import com.kamalesh.notetaker.exception.NotePathNotFoundException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NoteParser {

    private final Pattern identifierPattern = Pattern.compile("(([@#!^])[-_a-zA-Z]+)([-_a-zA-Z0-9])*");
    private final Pattern urlPattern =
            Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");

    public Note parse(File file) {

        if(!file.exists()) {
            throw new NotePathNotFoundException(file.getName());
        }
        Note note = new Note(file.getName());
        List<Identifier> superSetOfIdentifiersInNote = new ArrayList<Identifier>();
        Path path = FileSystems.getDefault().getPath(file.getAbsolutePath());
        try {
            List<String> noteFileLines = Files.readAllLines(path, Charset.defaultCharset());
            for(String line : noteFileLines) {
                List<Identifier> lineIdentifiers = parseIdentifiers(line);
                List<Identifier> urlIdentifiers = parseUrls(line);
                if(!lineIdentifiers.isEmpty()) {
                    superSetOfIdentifiersInNote.addAll(lineIdentifiers);
                }
                if(!urlIdentifiers.isEmpty()) {
                    superSetOfIdentifiersInNote.addAll(urlIdentifiers);
                }
            }
            Set<Identifier> uniqueIdentifiers = getUniqueIdentifiers(superSetOfIdentifiersInNote);
            note.addAllIdentifiers(superSetOfIdentifiersInNote);
            note.setUniqueIdentifiers(uniqueIdentifiers);
            int numberOfMentions = 0, numberOfReferences = 0;
            List<Identifier> allIdentifiers = note.getIdentifierList();
            for(Identifier identifier : allIdentifiers) {
                if(identifier.isMention()){
                    numberOfMentions++;
                }
            }
            note.setNumberOfMentions(numberOfMentions);
            note.setNumberOfReferences(allIdentifiers.size() - numberOfMentions);
        } catch(IOException e) {

        }
        return note;
    }

    private List<Identifier> parseIdentifiers(String line) {

        List<Identifier> identifiers = new ArrayList<Identifier>();
        Matcher identifierMatcher = identifierPattern.matcher(line);
        while(identifierMatcher.find()) {
            String matchingIdentifier = identifierMatcher.group();
            Identifier identifier = null;
            switch (matchingIdentifier.charAt(0)) {
                case '@':
                    identifier = new Identifier(matchingIdentifier.substring(1), IdentifierType.INDIVIDUAL);
                    break;

                case '#':
                    identifier = new Identifier(matchingIdentifier.substring(1), IdentifierType.TOPIC);
                    break;

                case '!':
                    identifier = new Identifier(matchingIdentifier.substring(1), IdentifierType.UNIQUE_ID);
                    break;

                case '^':
                    identifier = new Identifier(matchingIdentifier.substring(1), IdentifierType.REFERENCE);
                    break;

                default:
                    break;
            }
            if(identifier != null) {
                identifiers.add(identifier);
            }
        }
        return identifiers;
    }

    private List<Identifier> parseUrls(String line) {
        List<Identifier> identifiers = new ArrayList<Identifier>();
        Matcher identifierMatcher = urlPattern.matcher(line);
        while(identifierMatcher.find()) {
            String matchingIdentifier = identifierMatcher.group();
            identifiers.add(new Identifier(matchingIdentifier, IdentifierType.URL));
        }
        return identifiers;
    }

    private Set<Identifier> getUniqueIdentifiers(List<Identifier> identifiers) {
        Set<Identifier> identifierSet = new HashSet<Identifier>();
        for(Identifier identifier: identifiers) {
            if(IdentifierType.UNIQUE_ID.name().equals(identifier.getIdentifierType())) {
                identifierSet.add(identifier);
            }
        }
        return identifierSet;
    }

    private List<Identifier> removeUniqueIdentifiersAndReturnRest(List<Identifier> identifiers) {

        List<Identifier> identifierList = new ArrayList<Identifier>();
        for(Identifier identifier: identifiers) {
            if(!IdentifierType.UNIQUE_ID.name().equals(identifier.getIdentifierType())) {
                identifierList.add(identifier);
            }
        }
        return identifierList;
    }
}
