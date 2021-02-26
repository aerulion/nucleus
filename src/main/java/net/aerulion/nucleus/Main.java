package net.aerulion.nucleus;

import net.aerulion.nucleus.api.chat.ChatUtils;
import net.aerulion.nucleus.api.console.ConsoleUtils;
import net.aerulion.nucleus.util.FileManager;
import net.aerulion.nucleus.util.HikariDataSourceManager;
import net.aerulion.nucleus.util.Messages;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.*;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements CommandExecutor, TabCompleter {
    public static Main plugin;

    @Override
    public void onEnable() {
        ConsoleUtils.sendColoredConsoleMessage(Messages.CONSOLE_ENABLING.get());
        plugin = this;
        PluginCommand pluginCommand = getCommand("nucleus");
        if (pluginCommand != null)
            pluginCommand.setExecutor(this);
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
        ChatUtils.sendChatDividingLine(commandSender, NamedTextColor.GRAY);
        commandSender.sendMessage("");
        ChatUtils.sendCenteredChatMessage(commandSender, Component.text("Nucleus").color(NamedTextColor.AQUA).decorate(TextDecoration.BOLD).append(Component.text(" v" + getDescription().getVersion()).color(NamedTextColor.GRAY)));
        ChatUtils.sendCenteredChatMessage(commandSender, Component.text("by aerulion").color(NamedTextColor.GRAY));
        commandSender.sendMessage("");
        ChatUtils.sendChatDividingLine(commandSender, NamedTextColor.GRAY);
        return true;
    }
}