package com.kamalesh.notetaker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class NotesBuilder {

    public List<Note> build(String folderPath) {

        List<Note> notes = new ArrayList<Note>();
        File presumedFolder = new File(folderPath);
        File[] filesInDirectory = null;
        if (presumedFolder.isDirectory()) {
            filesInDirectory = presumedFolder.listFiles();
        }
        NoteParser noteParser = new NoteParser();
        for (File file : filesInDirectory) {
            Note note = noteParser.parse(file);
            notes.add(note);
        }
        return notes;
    }
}
