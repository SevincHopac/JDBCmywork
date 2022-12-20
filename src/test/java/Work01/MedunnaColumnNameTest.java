package Work01;

import JDBCStudy.JDBCUtils;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedunnaColumnNameTest {


    // MedunnaColumnNameTest

    // User connects to the database
    // JdbcUtils.connectToDataBase("medunna.com","medunna_db","medunna_user","medunna_pass_987");
    // Statement statement = JdbcUtils.createStatement();
    // User sends the query to get the columns of room table
    // Assert verify one of column name is "room_type"
    // User closes the connection

    @Test

    public void Test() throws SQLException {

        JDBCUtils.connectToDatabase("medunna.com", "medunna_db", "medunna_user", "medunna_pass_987");
        Statement statement = JDBCUtils.createStatement();

        String query = "SELECT * FROM room;";

        ResultSet resultSet = JDBCUtils.executeQuery(query);

        ResultSetMetaData rsMetaData = resultSet.getMetaData();
        System.out.println("List of column names in the current table: ");

        List<String> columnNames = new ArrayList<>();

        int count = rsMetaData.getColumnCount();

        for (int i = 1; i <= count; i++) {
            System.out.println("rsMetaData.getColumnName(i) = " + rsMetaData.getColumnName(i));
            columnNames.add(rsMetaData.getColumnName(i));
        }

        Assert.assertTrue(columnNames.contains("room_type"));

        JDBCUtils.closeConAndStat();
    }
}
