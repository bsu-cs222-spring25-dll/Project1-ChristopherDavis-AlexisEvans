package bsu.edu.cs;

import java.io.InputStream;
import java.util.List;

public class Menu {
    WikipediaConnection wikipediaConnection = new WikipediaConnection();
    RevisionParser revisionParser;
    RevisionFormatter revisionFormatter = new RevisionFormatter();
    UserResponse userResponse = new UserResponse();

    public void runMenu() {
        userResponse.PrintSearchPrompt();
        String userInput = userResponse.TakeUserInput();
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
        String userInput = userResponse.getUserInput();
        InputStream wikiResponse = wikipediaConnection.searchWikipediaUrl(userInput);
        revisionParser = new RevisionParser(wikiResponse);
        System.out.println(revisionParser.retrieveDestination());
        revisionFormatter.printRevisionList(revisionList);
    }

    private boolean validateUserInput(String userInput){
        return !userInput.isEmpty();
    }








}
