package JDBCStudy;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class Runner {
    public static void main(String[] args) {

        Connection connection =JDBCUtils.connectToDatabase("localhost","postgres","postgres","341416");

        Statement statement = JDBCUtils.createStatement();

    //    JDBCStudy.JDBCUtils.execute("CREATE TABLE persons ( name VARCHAR(20), id INT , address VARCHAR(80) )");

        JDBCUtils.insertDataToTable("persons","name 'ada'","id 10","address 'adana'");

        List<Object> columnValues =     JDBCUtils.getColumnList("name","persons");
        System.out.println("columnValues = " + columnValues);


        JDBCUtils.closeConAndStat();
    }
}
