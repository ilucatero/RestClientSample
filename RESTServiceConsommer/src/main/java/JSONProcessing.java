import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

/**
 * Created by Wizard on 23/01/2016.
 */
public class JSONProcessing {
    private static String directory=System.getProperty("user.dir");

    private JSONProcessing(){}

    public static String JSON2FiledCVS_Position(String jsonString){

        try {
            JSONObject output = new JSONObject("{data:"+jsonString+"}");
            JSONArray docs = output.getJSONArray("data");

            Long _id = docs.getJSONObject(0).getLong("_id");
            String name = docs.getJSONObject(0).getString("name");
            String type = docs.getJSONObject(0).getString("type");
            JSONObject geo_position = docs.getJSONObject(0).getJSONObject("geo_position");
            Double lat = geo_position.getDouble("latitude");
            Double lon = geo_position.getDouble("longitude");

            String fileName = _id + name + type + lat + "_"+lon;
            File file=new File(directory+"/Position_CVSFiles/"+fileName+".csv");
            String csv = CDL.toString(docs);
            FileUtils.writeStringToFile(file, csv);
            System.out.println("Path:"+file.getPath());

            return csv;

        } catch (JSONException e) { // Error parsing the JSON string
            e.printStackTrace();
        } catch (IOException e) { // File cannot be saved
            e.printStackTrace();
        }
        return null;
    }
}
