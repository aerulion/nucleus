package net.aerulion.nucleus;

import net.aerulion.nucleus.api.chat.ChatUtils;
import net.aerulion.nucleus.api.color.Color;
import net.aerulion.nucleus.util.FileManager;
import net.aerulion.nucleus.util.GuiHandler;
import net.aerulion.nucleus.util.HikariDataSourceManager;
import net.aerulion.nucleus.util.Message;
import net.aerulion.nucleus.util.messaging.MessagingManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Main extends JavaPlugin implements CommandExecutor, TabCompleter {

  public static Main plugin;
  public static MessagingManager messagingManager;
  public static GuiHandler guiHandler;

  @Override
  public void onEnable() {
    plugin = this;
    messagingManager = new MessagingManager();
    guiHandler = new GuiHandler();
    Message.ENABLING_PLUGIN.console();
    @Nullable PluginCommand pluginCommand = getCommand("nucleus");
    if (pluginCommand != null) {
      pluginCommand.setExecutor(this);
    }
    if (!FileManager.setDefaultMySQLConfig()) {
      Message.ERROR_DEFAULT_MYSQL_CONFIG.console();
    }
    FileManager.loadMySQLConfig();
    HikariDataSourceManager.connectToDatabase();
    getServer().getPluginManager().registerEvents(guiHandler, this);
    Message.PLUGIN_ENABLED.console();
  }

  @Override
  public void onDisable() {
    Message.DISABLING_PLUGIN.console();
    HikariDataSourceManager.hikariDataSource.close();
    Message.PLUGIN_DISABLED.console();
  }

  @Override
  public boolean onCommand(@NotNull CommandSender commandSender, Command command, String label,
      String[] args) {
    ChatUtils.sendChatDividingLine(commandSender, NamedTextColor.GRAY);
    commandSender.sendMessage("");
    ChatUtils.sendCenteredChatMessage(commandSender,
        Component.text("\uD83D\uDD25 ")
            .color(Color.NUCLEUS_PRIMARY)
            .append(Component.text("Nucleus").decorate(TextDecoration.BOLD))
            .append(Component.text(" v" + getDescription().getVersion())
                .color(Color.TEXT)
                .decoration(TextDecoration.BOLD,
                    TextDecoration.State.FALSE)));
    ChatUtils.sendCenteredChatMessage(commandSender,
        Component.text("by aerulion").color(Color.TEXT));
    commandSender.sendMessage("");
    ChatUtils.sendChatDividingLine(commandSender, NamedTextColor.GRAY);
    return true;
  }
}