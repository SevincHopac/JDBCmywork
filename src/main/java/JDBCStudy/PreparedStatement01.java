package JDBCStudy;

import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","341416");
        Statement statement = connection.createStatement();

        //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.

        String sql1 = " UPDATE companies SET number_of_employees = ? WHERE company = ? ";

        PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);

        preparedStatement1.setInt(1,9999);
        preparedStatement1.setString(2,"IBM");

        int number_of_update = preparedStatement1.executeUpdate();
        System.out.println("number_of_update = " + number_of_update);

        ResultSet resultSet = statement.executeQuery("SELECT * FROM companies");

        while (resultSet.next()){
            System.out.println(resultSet.getInt(1) + "--" + resultSet.getString(2) + "--" + resultSet.getInt(3));
        }


        //2. Örnek: Prepared statement kullanarak company adı GOOGLE olan number_of_employees değerini 5555 olarak güncelleyin.

        preparedStatement1.setInt(1,5555);
        preparedStatement1.setString(2,"GOOGLE");

        ResultSet resultSet2 = statement.executeQuery("SELECT * FROM companies");
        System.out.println("--------------------------------------------");

        while (resultSet2.next()){
            System.out.println(resultSet2.getInt(1) + "--" + resultSet2.getString(2) +"--"+ resultSet2.getInt(3));
        }

        connection.close();
        statement.close();
        resultSet.close();
        resultSet2.close();
        preparedStatement1.close();

    }
}
