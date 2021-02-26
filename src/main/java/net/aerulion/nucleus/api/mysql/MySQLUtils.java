package net.aerulion.nucleus.api.mysql;

import lombok.experimental.UtilityClass;
import net.aerulion.nucleus.util.HikariDataSourceManager;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * A utility class for accessing the nucleus connection pool
 */
@UtilityClass
public final class MySQLUtils {

    /**
     * Gets an available connection from the nucleus connection pool
     *
     * @return a connection to the nucleus connection pool
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        return HikariDataSourceManager.hikariDataSource.getConnection();
    }
}