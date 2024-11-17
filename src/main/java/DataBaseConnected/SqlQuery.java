package DataBaseConnected;

import java.util.List;

public class SqlQuery {

    public static String createQuery(List<String> selectFields, String tableName) {
        StringBuilder query;
        query = new StringBuilder("SELECT");
        if (selectFields.isEmpty())
            query.append(" *");
        else {
            for (String field : selectFields) {
                query.append(field);
                query.append(" ,");
            }
            query.deleteCharAt(query.length() - 1);
        }
        query.append("FROM ");
        query.append(tableName);

        return query.toString();
    }
}