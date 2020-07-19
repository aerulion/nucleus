package net.aerulion.nucleus.api.mysql;

import net.aerulion.nucleus.util.HikariDataSourceManager;

import java.sql.Connection;
import java.sql.SQLException;

public class MySQLUtils {

    public static Connection getConnection() throws SQLException {
        return HikariDataSourceManager.hikariDataSource.getConnection();
    }
}
