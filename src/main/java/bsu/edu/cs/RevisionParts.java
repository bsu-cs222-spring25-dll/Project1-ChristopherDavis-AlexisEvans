package bsu.edu.cs;

public class RevisionParts {
    public String name;
    protected String timeStampOfRevision;

    public RevisionParts(String name,String timeStampOfRevision){
        this.name = name;
        this.timeStampOfRevision = timeStampOfRevision;
    }
}