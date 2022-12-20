package JDBCStudy;

import java.sql.*;

public class CallableStatement1 {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","341416");
        Statement statement = connection.createStatement();

        String sql1 = "CREATE OR REPLACE FUNCTION toplamaF(x NUMERIC ,y NUMERIC) " +
                "RETURNS NUMERIC " +
                "LANGUAGE plpgsql " +
                " AS $$ BEGIN RETURN x+y; END $$ ";

        statement.execute(sql1);

        CallableStatement callableStatement1 = connection.prepareCall("{? = call toplamaF(?,?)}");

        callableStatement1.registerOutParameter(1,Types.NUMERIC);
        callableStatement1.setInt(2,5);
        callableStatement1.setInt(3,4);

        callableStatement1.execute();

        System.out.println(callableStatement1.getBigDecimal(1));


        //2. Örnek: Koninin hacmini hesaplayan bir function yazın.

        String sql2 = " CREATE OR REPLACE FUNCTION koniHacmi(r NUMERIC, h NUMERIC) " +
                " RETURNS NUMERIC " +
                " LANGUAGE plpgsql " +
                " AS $$ BEGIN RETURN ((3.14)*r*r*h)/3; END $$ ";

        statement.execute(sql2);

        CallableStatement callableStatement2 = connection.prepareCall("{ ? = call koniHacmi( ? , ? )}");

        callableStatement2.registerOutParameter(1,Types.NUMERIC);
        callableStatement2.setInt(2,3);
        callableStatement2.setInt(3,8);

        callableStatement2.execute();

        System.out.printf("%.2f", callableStatement2.getBigDecimal(1));


    }
}
