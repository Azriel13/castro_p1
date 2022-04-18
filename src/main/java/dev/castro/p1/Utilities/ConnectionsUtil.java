package dev.castro.p1.Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionsUtil {
    public static Connection createConnection(){

        try {
            Connection conn = DriverManager.getConnection(System.getenv("p1_Postgres"));
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
