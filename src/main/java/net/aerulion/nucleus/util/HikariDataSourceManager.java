package net.aerulion.nucleus.util;

import com.zaxxer.hikari.HikariDataSource;
import net.aerulion.nucleus.api.console.ConsoleUtils;

public class HikariDataSourceManager {

    public static HikariDataSource hikariDataSource;
    public static String serverName;
    public static String port;
    public static String databaseName;
    public static String user;
    public static String password;

    public static void connectToDatabase() {
        final long start = System.currentTimeMillis();
        ConsoleUtils.sendColoredConsoleMessage(Messages.CONSOLE_CONNECTION_POOL_INITIALIZING.get());
        hikariDataSource = new HikariDataSource();
        hikariDataSource.setMaximumPoolSize(10);
        hikariDataSource.setMinimumIdle(10);
        hikariDataSource.setConnectionTimeout(5000L);
        hikariDataSource.setValidationTimeout(2000L);
        hikariDataSource.setPoolName("NucleusConnectionPool");
        hikariDataSource.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        hikariDataSource.addDataSourceProperty("serverName", serverName);
        hikariDataSource.addDataSourceProperty("port", port);
        hikariDataSource.addDataSourceProperty("databaseName", databaseName);
        hikariDataSource.addDataSourceProperty("user", user);
        hikariDataSource.addDataSourceProperty("password", password);
        hikariDataSource.addDataSourceProperty("useSSL", false);
        hikariDataSource.addDataSourceProperty("cachePrepStmts", true);
        hikariDataSource.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariDataSource.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ConsoleUtils.sendColoredConsoleMessage(Messages.CONSOLE_CONNECTION_POOL_INITIALIZED.get() + (System.currentTimeMillis() - start) + "ms");
    }
}