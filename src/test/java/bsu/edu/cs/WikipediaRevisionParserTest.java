package bsu.edu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

public class WikipediaRevisionParserTest {
    @Test
    public void testParse(){
        RevisionParser parser = new RevisionParser();
        InputStream testDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("sample.json");
        String timeStamp = parser.parse(testDataStream);
        Assertions.assertEquals("", timeStamp);

    }
}
