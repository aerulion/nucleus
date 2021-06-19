package net.aerulion.nucleus.api.hibernate;

import lombok.experimental.UtilityClass;
import net.aerulion.nucleus.util.HikariDataSourceManager;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.jetbrains.annotations.NotNull;

/**
 * A utility class for hibernate methods
 */
@UtilityClass
public final class HibernateUtil {

    /**
     * Creates a new configuration with the nucleus default settings
     *
     * @param debug boolean whether debug messages are enabled
     * @return the generated configuration
     */
    public static @NotNull Configuration getPresetConfiguration(boolean debug) {
        @NotNull Configuration configuration = new Configuration();
        configuration.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        configuration.setProperty(Environment.URL, "jdbc:mysql://" + HikariDataSourceManager.serverName + ":" +
                HikariDataSourceManager.port + "/" + HikariDataSourceManager.databaseName + "?useSSL=false");
        configuration.setProperty(Environment.USER, HikariDataSourceManager.user);
        configuration.setProperty(Environment.PASS, HikariDataSourceManager.password);
        configuration.setProperty(Environment.HBM2DDL_AUTO, "update");
        configuration.setProperty(Environment.SHOW_SQL, String.valueOf(debug));
        configuration.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        configuration.setProperty(Environment.CONNECTION_PROVIDER, "org.hibernate.hikaricp.internal.HikariCPConnectionProvider");
        configuration.setProperty("hibernate.hikari.connectionTimeout", "20000");
        configuration.setProperty("hibernate.hikari.minimumIdle", "10");
        configuration.setProperty("hibernate.hikari.maximumPoolSize", "20");
        configuration.setProperty("hibernate.hikari.idleTimeout", "300000");
        return configuration;
    }
}