package bsu.edu.cs;

import java.io.InputStream;
import java.util.List;

public class Menu {
    WikipediaConnection wikipediaConnection = new WikipediaConnection();
    RevisionParser revisionParser;
    RevisionFormatter revisionFormatter = new RevisionFormatter();
    UserResponse userResponse = new UserResponse();

    //Inspired by code by Nolan Meyer and Dakota Coughlin
    public void runMenu() {
        userResponse.PrintSearchPrompt();
        String userInput = userResponse.TakeUserInput();
        if(verifyUserInput(userInput)) {
            List<RevisionParts> revisionList = inputSearch(userInput);
            if (!revisionList.isEmpty()) {
                inputSearchPrint(revisionList);
            }
        }
        else{
            System.out.println("You probably wanted to search for something so either what you typed doesn't exist or you just typed it wrong. Unless you wanted to see this specific message then congratulations");
        }
    }

    private List<RevisionParts> inputSearch(String userInput) {
        InputStream wikiResponse = wikipediaConnection.searchWikipediaUrl(userInput);
        revisionParser = new RevisionParser(wikiResponse);
        return revisionParser.parseWikipediaResponse();
    }

    private void inputSearchPrint(List<RevisionParts> revisionList){
        String userInput = userResponse.getUserInput();
        InputStream wikiResponse = wikipediaConnection.searchWikipediaUrl(userInput);
        revisionParser = new RevisionParser(wikiResponse);
        System.out.println(revisionParser.retrieveDestination());
        revisionFormatter.printRevisionList(revisionList);
    }

    protected boolean verifyUserInput(String userInput){
        return !userInput.isEmpty();
    }
}