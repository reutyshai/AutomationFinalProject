package DataBaseConnected;

import java.sql.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseAccessor {

    public static List<HashMap<String, Object>> fetchDataFromDatabase(List<String> fieldsToFetch, String tableName) {
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
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return results;

    }

    public static HashMap<String, Object> fetchDataFromDatabaseById(List<String> fieldsToFetch, String tableName, int id) {
        String query = SqlQuery.createQueryById(fieldsToFetch, tableName);
        HashMap<String, Object> result = new HashMap<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    for (String field : fieldsToFetch) {
                        Object value = resultSet.getObject(field);
                        result.put(field, value);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static HashMap<String, Object> fetchDataFromDatabase(List<String> fieldsToFetch, String tableName, String condition) {
        String query = SqlQuery.createQueryByCondition(fieldsToFetch, tableName);
        HashMap<String, Object> result = new HashMap<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, condition);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    for (String field : fieldsToFetch) {
                        Object value = resultSet.getObject(field);
                        result.put(field, value);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

//    public static List<HashMap<String, Object>> fetchDataFromDatabaseWithoutId(List<String> fieldsToFetch, String tableName, int id) {
//        String query = SqlQuery.createQueryWithoutConcreteRow(fieldsToFetch, tableName);
//        List<HashMap<String, Object>> results = new ArrayList<>();
//
//        try (Connection connection = DatabaseConnection.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//
//            preparedStatement.setInt(1, id);
//
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                while (resultSet.next()) {
//                    HashMap<String, Object> row = new HashMap<>();
//                    for (String field : fieldsToFetch) {
//                        row.put(field, resultSet.getInt(field));
//                    }
//                    results.add(row);
//                }
//            }
//        } catch (SQLException e) {
//
//            throw new RuntimeException(e);
//        }
//        return results;
//
//    }
//
//    public static List<HashMap<String, Object>> simulateInsert(HashMap<String, Object> updateFields, String tableName) {
//
//        List<HashMap<String, Object>> simulatedDatabase = fetchDataFromDatabase(new ArrayList<>(updateFields.keySet()), tableName);
//
//        HashMap<String, Object> newRow = new HashMap<>();
//
//        int newId = simulatedDatabase.size() + 1;
//        newRow.put("id", newId);
//
//        for (Map.Entry<String, Object> entry : updateFields.entrySet()) {
//            newRow.put(entry.getKey(), entry.getValue());
//        }
//
//        simulatedDatabase.add(newRow);
//        return simulatedDatabase;
//    }

}

