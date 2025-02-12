package bsu.edu.cs;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WikipediaConnection {
    //Searches wikipedia by taking in a String of "articleTitle"
    public InputStream searchWikipediaUrl(String articleTitle) {
        String wikipediaUrl = createWikipediaUrl(articleTitle);
        return retrieveWikipediaData(wikipediaUrl);
    }

    //Responsible for constructing the url and returning that string
    public String createWikipediaUrl(String articleTitle){
        String encodeTitle = URLEncoder.encode(articleTitle, StandardCharsets.UTF_8);
        return "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles="
                + encodeTitle + "&rvprop=timestamp|user&rvlimit=21&redirects";
    }

    //Gets the data from wikipedia in the form of an input stream.
    //Code heavily inspired by Nolan Meyer and Dakota Coughlin
    //Code heavily inspired by Lizzie and Aja
    public InputStream retrieveWikipediaData(String wikipediaUrl){
        InputStream output = new InputStream() {
            @Override
            public int read(){
                return 0;
            }
        };try{
            URL urlConnection = new URL(wikipediaUrl);
            URLConnection connection = urlConnection.openConnection();
            connection.setRequestProperty("User-Agent",
                    "Revision Reporter/0.1 (Christopher.davis@bsu.edu)");
            output = connection.getInputStream();
        }catch (Exception e) {
            System.err.println("There seems to be a network error.");
        }
        return output;
    }
}