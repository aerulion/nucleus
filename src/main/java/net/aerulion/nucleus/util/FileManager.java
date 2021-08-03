package net.aerulion.nucleus.util;

import java.io.File;
import java.io.IOException;
import lombok.experimental.UtilityClass;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public final class FileManager {

  public static boolean setDefaultMySQLConfig() {
    try {
      @NotNull File file = new File("plugins/Nucleus", "mysql_config.yml");
      @NotNull FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
      fileConfiguration.options().copyDefaults(true);
      fileConfiguration.addDefault("HOST", "localhost");
      fileConfiguration.addDefault("PORT", "3306");
      fileConfiguration.addDefault("DATABASE", "minecraft");
      fileConfiguration.addDefault("USER", "minecraft");
      fileConfiguration.addDefault("PASSWORD", "pwd");
      fileConfiguration.save(file);
      return true;
    } catch (IOException exception) {
      return false;
    }
  }

  public static void loadMySQLConfig() {
    @NotNull File file = new File("plugins/Nucleus", "mysql_config.yml");
    @NotNull FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
    HikariDataSourceManager.serverName = fileConfiguration.getString("HOST");
    HikariDataSourceManager.port = fileConfiguration.getString("PORT");
    HikariDataSourceManager.databaseName = fileConfiguration.getString("DATABASE");
    HikariDataSourceManager.user = fileConfiguration.getString("USER");
    HikariDataSourceManager.password = fileConfiguration.getString("PASSWORD");
  }
}