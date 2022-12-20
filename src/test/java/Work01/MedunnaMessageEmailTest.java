package Work01;

import JDBCStudy.JDBCUtils;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedunnaMessageEmailTest {


    // MedunnaMessageEmailTest

    // User connects to the database
    // JdbcUtils.connectToDataBase("medunna.com","medunna_db","medunna_user","medunna_pass_987");
    // Statement statement = JdbcUtils.createStatement();
    // User sends the query to get the names of "email" column from "cmessage" table
    // Assert that there are some "cmessage" email "zeynep05@gmail.com".
    // User closes the connection



    @Test

    public void emailTest() throws SQLException {

        JDBCUtils.connectToDatabase("medunna.com","medunna_db","medunna_user","medunna_pass_987");
        Statement statement = JDBCUtils.createStatement();

        String query = "SELECT email FROM cmessage;";

        ResultSet resultSet =JDBCUtils.executeQuery(query);
        List<String> emails = new ArrayList<>();

        while (resultSet.next()){

            emails.add(resultSet.getString(1));

        }

        Assert.assertTrue(emails.contains("zeynep05@gmail.com"));

        JDBCUtils.closeConAndStat();
    }
}
