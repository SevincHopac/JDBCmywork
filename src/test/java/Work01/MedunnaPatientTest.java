package Work01;

import JDBCStudy.JDBCUtils;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedunnaPatientTest {

    // MedunnaPatientTest

    // User connects to the database
    // JdbcUtils.connectToDataBase("medunna.com","medunna_db","medunna_user","medunna_pass_987");
    // Statement statement = JdbcUtils.createStatement();
    // User sends the query to get the names of "patient_id" column from "appointment" table
    // Assert that there are some appointment patient_id "405892".
    // Assert verify patients have 20295
    // User closes the connection

    @Test

    public void patientTest() throws SQLException {



        JDBCUtils.connectToDatabase("medunna.com","medunna_db","medunna_user","medunna_pass_987");
        Statement statement = JDBCUtils.createStatement();

        String query = "SELECT patient_id FROM appointment";

        ResultSet resultSet = JDBCUtils.executeQuery(query);

        List<Integer> patientIds = new ArrayList<>();

        while (resultSet.next()){
            patientIds.add(resultSet.getInt(1));
        }

        Assert.assertTrue(patientIds.contains(405892));

        Assert.assertEquals(20314,patientIds.size());

        System.out.println("patientIds.size() = " + patientIds.size());


        JDBCUtils.closeConAndStat();

    }
}
