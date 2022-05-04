package dev.castro.p1.Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionsUtil {
    public static Connection createConnection(){

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://castrodb.cd72nyeyabcf.us-east-2.rds.amazonaws.com/p1db?user=postgres&password=AzrielLucifer13");
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
