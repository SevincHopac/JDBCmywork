package Work01;

import JDBCStudy.JDBCUtils;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedunnaStaffBirthDay {

    // MedunnaStaffBirthDay

    // User connects to the database
    // JdbcUtils.connectToDataBase("medunna.com","medunna_db","medunna_user","medunna_pass_987");
    // Statement statement = JdbcUtils.createStatement();
    // User sends the query to get the names of birth_date column from "staff" table
    // Assert that there are some staff birth_date "2022-12-03 23:00:00".
    // User closes the connection

    @Test

    public void Test() throws SQLException {


        JDBCUtils.connectToDatabase("medunna.com", "medunna_db", "medunna_user", "medunna_pass_987");
        Statement statement = JDBCUtils.createStatement();

        String query = "SELECT birth_date FROM staff;";

        ResultSet resultSet = JDBCUtils.executeQuery(query);
        List<String> birth_day_list = new ArrayList<>();

        while (resultSet.next()) {
            birth_day_list.add(resultSet.getString(1));
        }

        Assert.assertTrue(birth_day_list.contains("2022-12-03 23:00:00"));

        JDBCUtils.closeConAndStat();
    }

}
