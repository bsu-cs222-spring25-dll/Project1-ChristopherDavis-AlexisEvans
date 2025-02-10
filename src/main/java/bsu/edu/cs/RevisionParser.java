package bsu.edu.cs;

import com.jayway.jsonpath.JsonPath;

import java.io.InputStream;
import net.minidev.json.JSONArray;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;



public class RevisionParser {
    InputStream wikiData;


    public RevisionParser(InputStream wikiData) {
        this.wikiData = wikiData;
    }



    public List<RevisionParts> parseWikipediaResponse(){
        JSONArray parsedRevisions = retrieveRevisions();
        return convertRevisionsToList(parsedRevisions);
    }


    private JSONArray retrieveRevisions() {
        JSONArray output = new JSONArray();
        try {
            JSONArray revisionArray = JsonPath.read(wikiData,"$..revisions");
            output = (JSONArray) revisionArray.getFirst();
        }catch (Exception e) {
            System.err.println("No Wikipedia article could be found. So either it doesn't exist or what you entered was spelled wrong.");
        }
        return output;

    }

    private List<RevisionParts> convertRevisionsToList(JSONArray array) {
        List<RevisionParts> revisionsList = new ArrayList<>();

        for(Object revision:array){
            if(revision instanceof LinkedHashMap<?,?>) {
                LinkedHashMap<String, String> revisionConverted = (LinkedHashMap<String, String>) revision;
                revisionsList.add(new RevisionParts(revisionConverted.get("user"), revisionConverted.get("timestamp")));
            }
        }
        return revisionsList;
    }

    public String retrieveDestination(){
        String output = "";
        try {
            JSONArray parsedRedirect = JsonPath.read(wikiData,"$..to");
            if(!parsedRedirect.isEmpty()){
                output = String.format("Directed to %s",parsedRedirect.getFirst());
            }

        }catch (Exception e) {
            System.err.println("There seems to be a network error....sorry...");
        }
        return output;
    }





}
