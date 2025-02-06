package bsu.edu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MenuTest {
    @Test
    public void recordingUserResponse(){
        Menu menu = new Menu();
        String result = menu.TakeUserResponse();
        Assertions.assertEquals("Oranges", result);





    }

}
