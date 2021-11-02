package ru.vsu.cs.group7.storage.JDBCStorage.persistence;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

//    private final static String DB_URL = "jdbc:mysql://localhost:3306/archivemanager"; //"jdbc:h2:../catalog";
//    private final static String DB_USER = "root";
//    private final static String DB_PASS = "root";

    private static ConnectionManager instance;
    private final HikariDataSource ds;

    private ConnectionManager() {
        ds = new HikariDataSource(new HikariConfig("src/main/resources/db.properties"));
    }

    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
