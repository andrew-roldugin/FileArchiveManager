package ru.vsu.cs.group7.storage.JDBCStorage;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/archivemanager"; //"jdbc:h2:../catalog";
    private final static String DB_USER = "root";
    private final static String DB_PASS = "root";

    private static ConnectionManager instance;

    private ConnectionManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to find db driver: " + e.getMessage());
        }
    }

    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
}
