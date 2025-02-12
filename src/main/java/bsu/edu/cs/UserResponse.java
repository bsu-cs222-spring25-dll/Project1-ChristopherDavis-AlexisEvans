package bsu.edu.cs;

import java.util.Scanner;

public class UserResponse {
    String userInput = "";

//getter
    public String getUserInput() {
        return userInput;
    }

//setter
    public void setUserResponse(String userResponse) {
        this.userInput = userResponse;
    }

    public void PrintSearchPrompt(){
        System.out.println("Please enter in the topic you wish to see the recent updates on Wikipedia for. If you want...");
    }

    //Uses a scanner to take in the user's response and returns the value
    public String TakeUserInput(){
        Scanner scanner = new Scanner(System.in);
        userInput = scanner.nextLine();
        return userInput;
    }
}