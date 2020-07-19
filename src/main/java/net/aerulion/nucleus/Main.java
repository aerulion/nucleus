package net.aerulion.nucleus;

import net.aerulion.nucleus.api.chat.ChatUtils;
import net.aerulion.nucleus.api.console.ConsoleUtils;
import net.aerulion.nucleus.util.FileManager;
import net.aerulion.nucleus.util.HikariDataSourceManager;
import net.aerulion.nucleus.util.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements CommandExecutor, TabCompleter {
    public static Main plugin;

    @Override
    public void onEnable() {
        ConsoleUtils.sendColoredConsoleMessage(Messages.CONSOLE_ENABLING.get());
        plugin = this;
        getCommand("nucleus").setExecutor(this);
        if (!FileManager.setDefaultMySQLConfig())
            ConsoleUtils.sendColoredConsoleMessage(Messages.CONSOLE_ERROR_DEFAULT_MYSQL_CONFIG.get());
        FileManager.loadMySQLConfig();
        HikariDataSourceManager.connectToDatabase();
        ConsoleUtils.sendColoredConsoleMessage(Messages.CONSOLE_PLUGIN_ENABLED.get());
    }

    @Override
    public void onDisable() {
        ConsoleUtils.sendColoredConsoleMessage(Messages.CONSOLE_DISABLING.get());
        HikariDataSourceManager.hikariDataSource.close();
        ConsoleUtils.sendColoredConsoleMessage(Messages.CONSOLE_PLUGIN_DISABLED.get());
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        ChatUtils.sendChatDividingLine(commandSender, "§7");
        commandSender.sendMessage("");
        ChatUtils.sendCenteredChatMessage(commandSender, "§b§lNucleus§7 v" + getDescription().getVersion());
        ChatUtils.sendCenteredChatMessage(commandSender, "§7by aerulion");
        commandSender.sendMessage("");
        ChatUtils.sendChatDividingLine(commandSender, "§7");
        return true;
    }
}