package com.nice.coday;

import com.opencsv.CSVReader;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.util.Arrays;

public class MyCSVToJsonConverter {

    public JSONArray  Converter(String csvFile) {
        
        JSONArray jsonArray = new JSONArray();
        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] headers = reader.readNext(); // Read the first line for headers
            
            
            String[] line;
            while ((line = reader.readNext()) != null) {
                JSONObject jsonObject = new JSONObject();
                for (int i = 0; i < headers.length; i++) {
                    jsonObject.put(headers[i], line[i]);
                }
                jsonArray.put(jsonObject);
            }
            
            
        } catch (Exception e) {
            
            e.printStackTrace();
            
        }
        return jsonArray;
    }
}
