package JDBCStudy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JDBCUtils {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static Connection connectToDatabase(String hostName, String databaseName, String userName, String password) {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://" + hostName + ":5432/" + databaseName, userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (connection != null)
            System.out.println(" Connection success !");
        else System.out.println(" Connection Fail !");

        return connection;
    }


    public static Statement createStatement() {

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }

    public static void createTable(String tableName, String... columnName_dataType) {

        StringBuilder columnName_dataValue = new StringBuilder("");

        for (String w : columnName_dataType) {
            columnName_dataValue.append(w).append(",");
        }
        columnName_dataValue.deleteCharAt(columnName_dataValue.length() - 1);


        try {
            statement.execute("CREATE " + tableName + "(" + columnName_dataValue + ")");
            System.out.println("Table " + tableName + " successfully created !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static boolean execute(String sql) {

        boolean isExecute;

        try {
            isExecute = statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return isExecute;
    }

    public static ResultSet executeQuery(String sql) {

        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public static int executeUpdate(String sql) {
        int numberOfUpdate;

        try {
            numberOfUpdate = statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return numberOfUpdate;
    }

    public static void closeConAndStat() {

        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Table'a data girme methodu

    public static void insertDataToTable(String tableName, String... columnNameValue) {

        StringBuilder columnNames = new StringBuilder("");
        StringBuilder values = new StringBuilder("");

        for (String w : columnNameValue) {

            columnNames.append(w.split(" ")[0]).append(",");
            values.append(w.split(" ")[1]).append(",");
        }

        columnNames.deleteCharAt(columnNames.lastIndexOf(","));
        values.deleteCharAt(values.lastIndexOf(","));

        // INSERT INTO tableName VALUES(aaa,ad,sd,fds);
        //"INSERT INTO tableName( id, name, address ) VALUES(123, 'john', 'new york')"

        String query = "INSERT INTO " + tableName + "(" + columnNames + ") VALUES(" + values + ")";

        execute(query);
        System.out.println("Data " + tableName + " tablosuna girildi.");

    }


    //Sütun Değerlerini List içerisine alan method

    public static List<Object> getColumnList(String columnName, String tableName) {

        List<Object> columnData = new ArrayList<>();

        String query = "SELECT " + columnName + " FROM " + tableName;

        executeQuery(query);


        try {
            while (resultSet.next()) {
                columnData.add(resultSet.getObject(columnName));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return columnData;
    }


}




