package bsu.edu.cs;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RevisionParserTest {
    @Test
    public void getFirstUsernameRevision(){
        InputStream jsonSampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("sample.json");
        assert jsonSampleFile != null;
        RevisionParser parser = new RevisionParser(jsonSampleFile);
        List<RevisionParts> firstRevisionName = parser.parseWikipediaResponse();
        assertEquals("Miklogfeather", firstRevisionName.getFirst().name);
    }

    @Test
    public void getFirstTimeStampRevision(){
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("sample.json");
        assert sampleFile != null;
        RevisionParser parser = new RevisionParser(sampleFile);
        List<RevisionParts> firstRevisionTimeStamp = parser.parseWikipediaResponse();
        assertEquals("2023-09-07T18:34:43Z", firstRevisionTimeStamp.getFirst().timeStampOfRevision);
    }

    @Test
    public void extractRedirectTest(){
        InputStream jsonSampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("sample.json");
        assert jsonSampleFile != null;
        RevisionParser revisionParser = new RevisionParser(jsonSampleFile);
        String parsedRedirect = revisionParser.retrieveDestination();
        assertEquals("Redirected to Frank Zappa",parsedRedirect);
    }
}