package JDBCStudy;

import java.sql.*;
import java.util.Scanner;

public class ExecuteUpdate {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","341416");
        Statement statement = connection.createStatement();

        //1. Örnek: number_of_employees değeri ortalama çalışan sayısından az olan number_of_employees değerlerini 16000 olarak UPDATE edin.

        String sql1 = "UPDATE companies " +
                      "SET number_of_employees = 16000 " +
                      "WHERE number_of_employees < (SELECT AVG(number_of_employees) FROM companies)";

        int number_of_updaterow = statement.executeUpdate(sql1);
        ResultSet resultSet1 = statement.executeQuery("SELECT * FROM companies");

        while (resultSet1.next()){
            System.out.println(resultSet1.getInt(1) + "--" + resultSet1.getString(2) + "--" + resultSet1.getInt(3));
        }

        connection.close();
        statement.close();
        resultSet1.close();
    }
}
