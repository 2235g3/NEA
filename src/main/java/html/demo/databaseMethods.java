package html.demo;

import java.sql.*;

public class databaseMethods {
    public static String[][] getData(String sql) {
        String[][] results;
        String DBLocation = System.getProperty("user.dir")  + "\\src\\main\\resources\\static\\database\\RestaurantNEA.accdb";
        try {
            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://" + DBLocation);
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery(sql);
            int rowCount = 0;
            if (resultSet.last()) {
                rowCount = resultSet.getRow();
                resultSet.first();
            }
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            String[] columnNames = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = resultSetMetaData.getColumnName(i);
            }
            results = new String[rowCount][columnCount];
            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < columnCount; j++) {
                    results[i][j] = resultSet.getString(columnNames[j]);
                }
            }
            resultSet.close();
            connection.close();
            return results;
        } catch (Exception e) {
            System.out.println("Error occurred");
            System.out.println(e);
            return null;
        }
    }
}

