package bsu.edu.cs;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class Menu {
    WikipediaConnection wikipediaConnection = new WikipediaConnection();
    RevisionParser revisionParser;
    RevisionFormatter revisionFormatter = new RevisionFormatter();
    Scanner scanner = new Scanner(System.in);
    UserResponse userResponse = new UserResponse();
    String userInput;

    public void runMenu(){
        userResponse.PrintSearchPrompt();
        String userInput = userResponse.TakeUserResponse();
        if(validateUserInput(userInput)) {

            List<RevisionParts> revisionList = inputSearch(userInput);

            if (!revisionList.isEmpty()) {
                inputSearchPrint(revisionList);
            }

        }else{
            System.err.println("Please give me a Wikipedia title!");
        }
    }

    private List<RevisionParts> inputSearch(String userInput) {
        InputStream wikiResponse = wikipediaConnection.searchWikipediaUrl(userInput);
        revisionParser = new RevisionParser(wikiResponse);
        return revisionParser.parseWikipediaResponse();
    }

    private void inputSearchPrint(List<RevisionParts> revisionList){
        System.out.println(revisionParser.extractRedirect());
        revisionFormatter.printRevisionList(revisionList);
    }

    private boolean validateUserInput(String userInput){
        return !userInput.isEmpty();
    }








}
