package JDBCStudy;

import java.sql.*;

public class ExecuteQuery {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","341416");
        Statement statement = connection.createStatement();


        //1. Örnek:  region id'si 1 olan "country name" değerlerini çağırın. (countries table)

        String sql1 = "SELECT country_name FROM countries WHERE region_id=1;";
        ResultSet resultSet = statement.executeQuery(sql1);

        while (resultSet.next()){
            System.out.println(resultSet.getString(1));
        }

        //2.Örnek: "region_id"nin 2'den büyük olduğu "country_id" ve "country_name" değerlerini çağırın

        String sql2 = "SELECT country_id, country_name FROM countries WHERE region_id > 2";
        ResultSet resultSet2 = statement.executeQuery(sql2);
        System.out.println("---------------------------------------------");

        while (resultSet2.next()){
            System.out.println(resultSet2.getString(1) + "--" + resultSet2.getString(2));
        }

        //3.Örnek: "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın. (companies table)

        String sql3 = "SELECT * FROM companies WHERE number_of_employees = (SELECT MIN(number_of_employees) FROM companies) ";
        ResultSet resultSet3 = statement.executeQuery(sql3);

        while (resultSet3.next()){
            System.out.println(resultSet3.getInt(1) + "--" + resultSet3.getString(2) + "--" + resultSet3.getInt(3));
        }

        connection.close();
        statement.close();

    }
}
