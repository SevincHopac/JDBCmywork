package JDBCStudy;

import java.sql.*;

public class ExecuteQuery2 {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","341416");
        Statement statement = connection.createStatement();


        //1. Örnek: companies tablosundan en yüksek ikinci number_of_employees değeri olan company ve number_of_employees değerlerini çağırın.


        //1.yol   ( 0FFSET VE FETCH NEXT )

        String sql1 = "SELECT company, number_of_employees FROM companies ORDER BY number_of_employees DESC OFFSET 1 ROW FETCH NEXT 1 ROW ONLY ";

        ResultSet resultSet1 = statement.executeQuery(sql1);
        resultSet1.next();
        System.out.println(resultSet1.getString(1)+"--"+resultSet1.getInt(2));

        //2.yol (Subquery)

        String sql2 = "SELECT company, number_of_employees " +
                      "FROM companies " +
                      "WHERE number_of_employees = (SELECT MAX(number_of_employees) " +
                                                    "FROM companies " +
                                                     "WHERE number_of_employees < (SELECT MAX(number_of_employees) FROM companies));";

        ResultSet resultSet2 = statement.executeQuery(sql2);
        while (resultSet2.next()){
            System.out.println(resultSet2.getString(1)+"--"+resultSet2.getInt(2));
        }

        connection.close();
        statement.close();
        resultSet1.close();
        resultSet2.close();
    }
}
