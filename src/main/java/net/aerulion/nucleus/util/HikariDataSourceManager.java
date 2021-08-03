package net.aerulion.nucleus.util;

import com.zaxxer.hikari.HikariDataSource;
import lombok.experimental.UtilityClass;
import net.aerulion.nucleus.api.messaging.Placeholder;
import org.jetbrains.annotations.Nullable;

@UtilityClass
public final class HikariDataSourceManager {

  public static HikariDataSource hikariDataSource;
  public static @Nullable String serverName;
  public static @Nullable String port;
  public static @Nullable String databaseName;
  public static @Nullable String user;
  public static @Nullable String password;

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