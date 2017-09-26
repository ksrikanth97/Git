package com.kamalesh.notetaker;

import com.kamalesh.notetaker.exception.InvalidReportRequestedException;
import com.kamalesh.notetaker.exception.NotePathMissingInputException;
import com.kamalesh.notetaker.exception.TooManyArgumentsException;

public class CommandArgsParser {


    protected String folderPath;
    protected String[] arrayOfMentionsOrKeywords;
    protected boolean reportNotesContainingAnyMentions;
    protected boolean reportNotesOrganizedByMention;
    protected boolean reportNotesContainingKeywords;
    protected boolean reportNotesOrganizedByKeyword;
    protected boolean reportNotesBySelectiveMentionOrKeywords;
    protected boolean reportNotesByTopologicalOrder;

    /**
     *
     * @param arguments
     * Usage:
     * -m -> Be able to report of all notes containing one or more mentions
     * -om -> Be able to generate a report of all notes, organized by mention
     * -k -> Be able to generate a report of all keywords
     * -ok -> Be able to generate a report of all notes, organized by keyword
     * -mk -> Be able to report notes by mention/keywords selectively
     * -t -> Report of all notes in topological order
     * Assumptions:
     * a note corresponds to a file in the folder.
       @ -> individual
       # -> topic
       ! -> unique_id
       ^ -> reference
       & -> url
       mention -> individual, topic, unique_id, url
       keyword -> unique_id

     */
    public void parse(String[] arguments) {

        if(arguments == null || arguments.length == 0) {
            System.out.println("No arguments provided. Please refer to usage.");
            printUsage();
            System.exit(0);
        } else {
          if(arguments.length > 4) {
              throw new TooManyArgumentsException();
          }
            if("-h".equals(arguments[0])) {
                printUsage();
                System.exit(0);
            } else if(!"-p".equals(arguments[0])) {
                throw new NotePathMissingInputException();
            } else {
                folderPath = arguments[1];
                if("-m".equals(arguments[2])) {
                    reportNotesContainingAnyMentions = true;
                } else if("-om".equals(arguments[2])) {
                    reportNotesOrganizedByMention = true;
                } else if("-k".equals(arguments[2])) {
                    reportNotesContainingKeywords = true;
                } else if("-ok".equals(arguments[2])) {
                    reportNotesOrganizedByKeyword = true;
                } else if("-mk".equals(arguments[2])) {
                    reportNotesBySelectiveMentionOrKeywords = true;
                    arrayOfMentionsOrKeywords = arguments[3].split(",");
                } else if("-t".equals(arguments[2])) {
                    reportNotesByTopologicalOrder = true;
                } else {
                    throw new InvalidReportRequestedException();
                }
            }


        }

    }


    private void printUsage() {
        System.out.println(
                "Only one report can be generated any a given time. And only the first valid option for the report will be recognized.\n" +
                "* Usage:\n" +
                "     * MainClass: NotesMain.java \n"   +
                "     * -p -> Enter first followed by file path before entering any commands below\n" +
                "     * -m -> Be able to report of all notes containing one or more mentions\n" +
                "     * -om -> Be able to generate a report of all notes, organized by mention\n" +
                "     * -k -> Be able to generate a report of all keywords\n" +
                "     * -ok -> Be able to generate a report of all notes, organized by keyword\n" +
                "     * -mk -> Be able to report notes by mention/keywords selectively\n" +
                "     * -t -> Report of all notes in topological order\n" +
                "     * Assumptions:\n" +
                "     * a note corresponds to a file in the folder.\n" +
                "       @ -> individual\n" +
                "       # -> topic\n" +
                "       ! -> unique_id\n" +
                "       ^ -> reference\n" +
                "       & -> url(just a symbol not really an character in note)\n" +
                "       mention -> individual, topic, unique_id, url\n" +
                "       keyword -> unique_id\n" +
                "     "
        );
    }


}
