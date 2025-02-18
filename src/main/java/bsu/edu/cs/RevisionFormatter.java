package bsu.edu.cs;

import java.util.List;

public class RevisionFormatter {
    public String printRevisionList(List<RevisionParts> revisionList){
        int line = 1;
        StringBuilder output = new StringBuilder();
        for(RevisionParts revision:revisionList){
            output.append(String.format("%d  %s",line,formatOutput(revision)));
            line++;
        }
        return output.toString();
    }
    public String formatOutput(RevisionParts revision){
        return String.format("Timestamp: %s Username: %s\n",revision.timeStampOfRevision,revision.name);
    }
}