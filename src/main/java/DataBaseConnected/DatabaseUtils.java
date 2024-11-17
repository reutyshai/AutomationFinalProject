package DataBaseConnected;

import java.sql.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseUtils {

    public static String fetchDataFromDatabase(List<String> fieldsToFetch, String tableName) throws SQLException, JsonProcessingException {
        String query = SqlQuery.createQuery(fieldsToFetch, tableName);
        List<HashMap<String, Object>> results = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                HashMap<String, Object> row = new HashMap<>();
                for (String field : fieldsToFetch) {
                    row.put(field, resultSet.getInt(field));
                }
                results.add(row);
            }
        }

        // המרה ל-JSON
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(results);
    }
}
