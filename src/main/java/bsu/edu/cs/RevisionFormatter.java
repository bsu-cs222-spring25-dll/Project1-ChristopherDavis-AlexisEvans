package bsu.edu.cs;

import java.util.List;

public class RevisionFormatter {
    public void printRevisionList(List<RevisionParts> revisionList){
        int lineNumber = 1;
        for(RevisionParts revision:revisionList){
            System.out.printf("%d.  %s",lineNumber,formatOutput(revision));
            lineNumber++;
        }

    }

    protected String formatOutput(RevisionParts revision){
        return String.format("Timestamp: %s Username: %s\n",revision.timeStampOfRevision,revision.name);
    }

}
