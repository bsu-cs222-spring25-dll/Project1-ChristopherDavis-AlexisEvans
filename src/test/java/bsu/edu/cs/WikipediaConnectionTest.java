package bsu.edu.cs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WikipediaConnectionTest {
    @Test
    public void createWikipediaUrlTest(){
        WikipediaConnection wikipediaConnection = new WikipediaConnection();
        assertEquals("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=Frank+Zappa&rvprop=timestamp|user&rvlimit=21&redirects", wikipediaConnection.createWikipediaUrl("Frank Zappa"));
    }
}
