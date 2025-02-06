package bsu.edu.cs;
import java.util.Scanner;

public class Menu {
    public void PrintSearchPrompt(){
        System.out.println("Please enter in the topic you wish to see the recent updates on Wikipedia for. ");
    }

    public String TakeUserResponse(){
        Scanner scanner = new Scanner(System.in);
        String userResponse = scanner.nextLine();
        return userResponse;
    }

}
