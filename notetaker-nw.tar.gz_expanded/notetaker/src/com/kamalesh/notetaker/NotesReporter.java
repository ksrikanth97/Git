package com.kamalesh.notetaker;

import java.util.*;

public class NotesReporter {

    private List<Note> notes;
    private Map<Identifier, List<Note>> identifierNoteMap;
    public NotesReporter(List<Note> notes) {
        this.notes = notes;
        identifierNoteMap = new HashMap<Identifier, List<Note>>();
        buildMap();
    }

    public void reportNotesContainingAnyMentions() {
        for(Note note: notes) {
            if(note.hasMentions()) {
                System.out.println("Note " + note.getFileName() + " has " + note.getNumberOfMentions() + " mention(s).");
            }
        }
    }

    public void reportNotesOrganizedByMention() {
        for(Map.Entry<Identifier, List<Note>> entry: identifierNoteMap.entrySet()) {
            Identifier identifier = entry.getKey();
            if(identifier.isMention()) {
                String noteString = getNoteString(entry.getValue());
                System.out.println("Mention " + identifier.getIdentifierString() + " is present in notes " + noteString
                        + " as (" + identifier.getIdentifierSymbol() + ")" + identifier.getIdentifierType());
            }
        }
    }

    public void reportNotesContainingKeywords() {
        for(Map.Entry<Identifier, List<Note>> entry: identifierNoteMap.entrySet()) {
            Identifier identifier = entry.getKey();
            if(identifier.isKeyword()) {
               System.out.println("Keyword Found: " + identifier.getIdentifierString());
            }
        }
    }

    public void reportNotesOrganizedByKeyword() {
        for(Map.Entry<Identifier, List<Note>> entry: identifierNoteMap.entrySet()) {
            Identifier identifier = entry.getKey();
            if(identifier.isKeyword()) {
                String noteName = entry.getValue().get(0).getFileName();
                System.out.println("Keyword " + identifier.getIdentifierString() + " is present in note " + noteName);
            }
        }
    }


    public void reportNotesBySelectiveMentionOrKeywords(String[] mentionOrKeywords) {

        for(Map.Entry<Identifier, List<Note>> entry: identifierNoteMap.entrySet()) {
            for (String mention : mentionOrKeywords) {
                Identifier identifier = entry.getKey();
                if(identifier.isMention() && identifier.getIdentifierString().equals(mention)) {
                    String noteString = getNoteString(entry.getValue());
                    System.out.println("Mention " + mention + " occurs as " + entry.getKey().getIdentifierType()+ " in files : " + noteString
                     );
                }
            }
        }
    }

    public void reportNotesByTopologicalOrder() {
        List<Note> sortableNotes = new ArrayList<Note>(notes);
        Collections.sort(sortableNotes, new Comparator<Note>() {
            @Override
            public int compare(Note o1, Note o2) {
                return o2.getNumberOfReferences() - o1.getNumberOfReferences();
            }
        });
        for(Note note : sortableNotes) {
            System.out.println("Note " + note.getFileName() + " has " + note.getNumberOfReferences() + " references.");
        }
    }

    private String getNoteString(List<Note> notes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Note note : notes) {
            stringBuilder.append(note.getFileName() + ",");
        }
        int length = stringBuilder.length();
        stringBuilder.deleteCharAt(length - 1);
        return stringBuilder.toString();
    }


    private void buildMap() {
        for(Note note: notes) {
            List<Identifier> identifierList = note.getIdentifierList();
            for(Identifier identifier: identifierList) {
                List<Note> notesRefList = identifierNoteMap.get(identifier);
                if(notesRefList != null) {
                    notesRefList.add(note);
                } else {
                    List<Note> refNotes = new ArrayList<Note>();
                    refNotes.add(note);
                    identifierNoteMap.put(identifier,refNotes);
                }
            }
        }
    }
}
