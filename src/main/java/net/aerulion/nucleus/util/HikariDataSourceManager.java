package net.aerulion.nucleus.util;

import com.zaxxer.hikari.HikariDataSource;
import net.aerulion.nucleus.api.messaging.Placeholder;

public class HikariDataSourceManager {

    public static HikariDataSource hikariDataSource;
    public static String serverName;
    public static String port;
    public static String databaseName;
    public static String user;
    public static String password;

    public static void connectToDatabase() {
        final long start = System.currentTimeMillis();
        Message.CONNECTION_POOL_INITIALIZING.console();
        hikariDataSource = new HikariDataSource();
        hikariDataSource.setMaximumPoolSize(10);
        hikariDataSource.setMinimumIdle(10);
        hikariDataSource.setConnectionTimeout(5000L);
        hikariDataSource.setValidationTimeout(2000L);
        hikariDataSource.setPoolName("NucleusConnectionPool");
        hikariDataSource.setJdbcUrl("jdbc:mysql://" + serverName + ":" + port + "/" + databaseName);
        hikariDataSource.addDataSourceProperty("user", user);
        hikariDataSource.addDataSourceProperty("password", password);
        hikariDataSource.addDataSourceProperty("useSSL", false);
        hikariDataSource.addDataSourceProperty("useAffectedRows", true);
        hikariDataSource.addDataSourceProperty("cachePrepStmts", true);
        hikariDataSource.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariDataSource.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        Message.CONNECTION_POOL_INITIALIZED.console(
                new Placeholder("%timestamp%", String.valueOf(System.currentTimeMillis() - start)));
    }
}