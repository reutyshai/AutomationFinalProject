package DataBaseConnected;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;

public class JsonConvert {
    public static String convertToJSON(HashMap<String, Object> result) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(result);
        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }
    }

    public static String convertToJSON(List<HashMap<String, Object>> results) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(results);
        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }
    }
}