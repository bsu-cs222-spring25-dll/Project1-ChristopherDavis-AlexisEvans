package bsu.edu.cs;

import com.jayway.jsonpath.JsonPath;
import java.io.IOException;
import java.io.InputStream;
import net.minidev.json.JSONArray;


public class RevisionParser {
    public String parse(InputStream testDataStream) {
        JsonPath.read(testDataStream, "$...timestamp");
        JSONArray result = (JSONArray) JsonPath.read(testDataStream, "$...timstamp");
        return result.get(0).toString();
    }
}
