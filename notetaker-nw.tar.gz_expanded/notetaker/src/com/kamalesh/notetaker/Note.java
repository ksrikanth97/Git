package com.kamalesh.notetaker;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* a note corresponds to a file in the folder.
@ -> individual
# -> topic
! -> unique_id
^ -> reference
& -> url
mention -> individual, topic, unique_id, url
keyword -> unique_id
*/
public class Note {

    private List<Identifier> identifierList;
    private String fileName;
    private Set<Identifier> uniqueIdentifiers;

    public boolean hasMentions() {
        return numberOfMentions > 0;
    }


    public int getNumberOfMentions() {
        return numberOfMentions;
    }

    public void setNumberOfMentions(int numberOfMentions) {
        this.numberOfMentions = numberOfMentions;
    }

    public int getNumberOfReferences() {
        return numberOfReferences;
    }

    public void setNumberOfReferences(int numberOfReferences) {
        this.numberOfReferences = numberOfReferences;
    }

    private int numberOfMentions, numberOfReferences;

    public Set<Identifier> getUniqueIdentifiers() {
        return uniqueIdentifiers;
    }

    public void setUniqueIdentifiers(Set<Identifier> uniqueIdentifiers) {
        this.uniqueIdentifiers = uniqueIdentifiers;
    }

    public Note(String fileName) {
        this.fileName = fileName;
        identifierList = new ArrayList<Identifier>();
    }

    public void addIdentifier(Identifier identifier) {
        identifierList.add(identifier);
    }

    public void addAllIdentifiers(List<Identifier> identifiers) {
        identifierList.addAll(identifiers);
    }

    public List<Identifier> getIdentifierList() {
        return this.identifierList;
    }

    public String getFileName() {
        return fileName;
    }
}
