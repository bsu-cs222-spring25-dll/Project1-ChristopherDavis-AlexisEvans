package bsu.edu.cs;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class WikipediaConnection {
    public String searchWikipediaUrl(String articleTitle) throws IOException {
        String wikipediaUrl = createWikipediaUrl(articleTitle);
        return getWikipediaData(wikipediaUrl);
    }

    public String createWikipediaUrl(String articleTitle){
        String encodeTitle = URLEncoder.encode(articleTitle, StandardCharsets.UTF_8);
        return "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles="
                + encodeTitle + "&rvprop=timestamp|user&rvlimit=21&redirects";
    }

    private String getWikipediaData(String wikipediaUrl)throws IOException {
        try {
            URL wikipediaUrlConnection = new URL(wikipediaUrl);
            URLConnection connection = wikipediaUrlConnection.openConnection();
            connection.setRequestProperty("User-Agent",
                    "CS222FirstProject/0.1 (Christopher.davis@bsu.edu)");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        } catch (Exception e) {
            System.err.println("Something went wrong with the connection");
            return null;
        }
    }


}
