package bsu.edu.cs;

import java.util.List;

public class RevisionFormatter {
    public void printRevisionList(List<RevisionParts> revisionList){
        int line = 1;
        for(RevisionParts revision:revisionList){
            System.out.printf("%d.  %s",line,formatOutput(revision));
            line++;
        }
    }
    public String formatOutput(RevisionParts revision){
        return String.format("Timestamp: %s Username: %s\n",revision.timeStampOfRevision,revision.name);
    }
}