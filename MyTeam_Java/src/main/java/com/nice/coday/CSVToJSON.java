import com.opencsv.CSVReader;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.util.Arrays;

public class CSVToJsonConverter {

    public static void main(String[] args) {
        String csvFile = "path/to/your/file.csv";

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] headers = reader.readNext(); // Read the first line for headers
            JSONArray jsonArray = new JSONArray();
            
            String[] line;
            while ((line = reader.readNext()) != null) {
                JSONObject jsonObject = new JSONObject();
                for (int i = 0; i < headers.length; i++) {
                    jsonObject.put(headers[i], line[i]);
                }
                jsonArray.put(jsonObject);
            }
            
            System.out.println(jsonArray.toString(4)); // Pretty print with 4 spaces indent
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
