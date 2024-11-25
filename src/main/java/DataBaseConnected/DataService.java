package DataBaseConnected;

import java.util.List;

public class DataService {
    public static String getData(List<String> fieldsToGet, String tableName) {
        return JsonConvert.convertToJSON(DatabaseAccessor.fetchDataFromDatabase(fieldsToGet, tableName));
    }

    public static String getById(List<String> fieldsToGet, String tableName, int id) {
        return JsonConvert.convertToJSON(DatabaseAccessor.fetchDataFromDatabaseById(fieldsToGet, tableName, id));
    }

    public static String getByCondition(List<String> fieldsToGet, String tableName, String condition) {
        return JsonConvert.convertToJSON(DatabaseAccessor.fetchDataFromDatabase(fieldsToGet, tableName, condition));
    }
}
