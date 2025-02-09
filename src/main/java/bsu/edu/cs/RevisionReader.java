package bsu.edu.cs;


import java.net.URL;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Scanner;

public class RevisionReader {
    public static void main(String[] artgs){
        RevisionReader revisionReader = new RevisionReader();
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        try{
            String timeStamp = revisionReader.getLatestReversionOf(line);
            System.out.println(timeStamp);
        } catch (IOException.ioException) {
            System.err.println("Network Connection Problem: ", ioException.getMessage());
        }
    }

    private String getLatestReversionOf(String articleTitle) throws IOException{
        String urlString = String.format("*instert time stamp here", articleTitle);
        String encodeUrlString = URLEncoder.encode(urlString, Charset.defaultCharset());
        try{
            URL url = new URL(encodeUrlString);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("");
            InputStream inputStream = connection.getInputStream();
            RevisionParser parser = new RevisionParser();
            String timeStamp = parser.parse(inputStream);
            return timeStamp;
        } catch (MalformedURLException malformedURLException){
            throw new RuntimeException(malformedURLException);
        }
    }


}
