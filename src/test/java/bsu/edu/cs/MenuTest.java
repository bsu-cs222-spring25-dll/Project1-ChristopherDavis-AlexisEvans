package bsu.edu.cs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {
    @Test
    public void verifyUserInput(){
        Menu menu = new Menu();
        assertTrue(menu.verifyUserInput("Test User"));
    }

    @Test
    public void verifyUserInputEmpty(){
        Menu menu = new Menu();
        assertFalse(menu.verifyUserInput(""));
    }
}
