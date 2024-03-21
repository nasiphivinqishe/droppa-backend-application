package com.example.droppabackend.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static Connection createConnectionViaUserPwd() throws Exception {
        String username = "postgres";
        String password = "postgres";
        String dbEndpoint = "postgresql://postgres:1_Uminathi@postgres:5432/droppa_v2";
        String jdbc_url = "jdbc:postgresql://localhost:5432/droppa_v2";

        try {
            Connection connection = DriverManager.getConnection(jdbc_url, username, password);
            return connection;
        } catch (SQLException e) {
            System.out.println("Failed to connect to PostgreSQL database!");
            e.printStackTrace();
            throw new Exception(e);
        }
    }
}
