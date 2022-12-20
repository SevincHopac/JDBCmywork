package JDBCStudy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","341416");
        Statement statement = connection.createStatement();


        // --Ex01: "workers" adinda bir table olusturup "worker_id","worker_name","worker_salary" fieldlari ekleyin

        boolean sql1 = statement.execute("CREATE TABLE workers(worker_id VARCHAR(20),worker_name VARCHAR(20),worker_salary INT)");
        System.out.println("sql1 = " + sql1);

        // --Ex02: Table a worker_address field ekleyerek alter yapin

        boolean sql2 = statement.execute("ALTER TABLE workers ADD worker_address VARCHAR(20)");
        System.out.println("sql2 = " + sql2);

        // --Ex03: workers table drop et

        boolean sql3 = statement.execute("DROP TABLE workers");
        System.out.println("sql3 = " + sql3);

        connection.close();
        statement.close();

    }
}
