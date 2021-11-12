package ru.vsu.cs.group7.storage.JDBCStorage.persistence;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {

//    private final static String DB_URL = "jdbc:mysql://localhost:3306/archivemanager"; //"jdbc:h2:../catalog";
//    private final static String DB_USER = "root";
//    private final static String DB_PASS = "root";

    private static ConnectionManager instance;
    private DataSource ds;
    private static final Logger logger = LoggerFactory.getLogger(ConnectionManager.class.getSimpleName());

    private ConnectionManager() {
//        InitialContext initContext = null;
//        try {
//            initContext = new InitialContext();
//            ds = (DataSource) initContext.lookup("java:comp/env/jdbc/FileArchiveManager");
//        } catch (NamingException e) {
////            e.printStackTrace();
//            logger.error(e.getMessage());
//        }
//        /~/projects/FileArchiveManager/src/main/resources
        ds = new HikariDataSource(new HikariConfig("/db.properties"));
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
