package bsu.edu.cs;

import java.util.Scanner;

public class UserResponse {
    String userResponse;

    public String getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(String userResponse) {
        this.userResponse = userResponse;
    }

    public UserResponse(){
        this.userResponse = userResponse;
    }
    public void PrintSearchPrompt(){

        System.out.println("Please enter in the topic you wish to see the recent updates on Wikipedia for. ");
    }

    public String TakeUserResponse(){
        Scanner scanner = new Scanner(System.in);
        userResponse = scanner.nextLine();
        return userResponse;
    }


}
