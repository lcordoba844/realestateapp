package util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static Connection conn = null;

    public static Connection connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inmobiliaria?useSSL=false&serverTimezone=UTC", "root", "Guthem121");

        return conn;
    }
}







