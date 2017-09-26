package com.kamalesh.notetaker;

import java.util.List;

public class NotesMain {


    public static void main(String[] args) {

        CommandArgsParser commandArgsParser = new CommandArgsParser();
        commandArgsParser.parse(args);
        NotesBuilder notesBuilder = new NotesBuilder();
        List<Note> notes = notesBuilder.build(commandArgsParser.folderPath);
        NotesReporter notesReporter = new NotesReporter(notes);

        if(commandArgsParser.reportNotesContainingAnyMentions) {
            notesReporter.reportNotesContainingAnyMentions();
        } else if(commandArgsParser.reportNotesOrganizedByMention) {
            notesReporter.reportNotesOrganizedByMention();
        } else if(commandArgsParser.reportNotesContainingKeywords) {
            notesReporter.reportNotesContainingKeywords();
        } else if(commandArgsParser.reportNotesOrganizedByKeyword) {
            notesReporter.reportNotesOrganizedByKeyword();
        } else if(commandArgsParser.reportNotesBySelectiveMentionOrKeywords) {
            notesReporter.reportNotesBySelectiveMentionOrKeywords(commandArgsParser.arrayOfMentionsOrKeywords);
        } else if(commandArgsParser.reportNotesByTopologicalOrder) {
            notesReporter.reportNotesByTopologicalOrder();
        }
    }


}
