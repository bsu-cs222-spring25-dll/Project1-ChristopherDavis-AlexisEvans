package bsu.edu.cs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RevisionFormatterTest {
    @Test
    public void testFormatOutput(){
        RevisionFormatter revisionFormatter = new RevisionFormatter();
        RevisionParts revisionParts = new RevisionParts("ModernDayTrilobite", "2023-09-07T17:21:48Z");
        String formattedOutput = revisionFormatter.formatOutput(revisionParts);
        assertEquals("Timestamp: 2023-09-07T17:21:48Z Username: ModernDayTrilobite\n", formattedOutput);
    }

}
