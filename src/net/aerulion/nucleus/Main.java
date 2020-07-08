package net.aerulion.nucleus;

import net.aerulion.nucleus.api.chat.ChatUtils;
import net.aerulion.nucleus.api.console.ConsoleUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements CommandExecutor, TabCompleter {
    public static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;
        ConsoleUtils.sendColoredConsoleMessage(Lang.CONSOLE_ENABLING);
        getCommand("nucleus").setExecutor(this);
        ConsoleUtils.sendColoredConsoleMessage(Lang.CONSOLE_PLUGIN_ENABLED);
    }

    @Override
    public void onDisable() {
        ConsoleUtils.sendColoredConsoleMessage(Lang.CONSOLE_DISABLING);
        ConsoleUtils.sendColoredConsoleMessage(Lang.CONSOLE_PLUGIN_DISABLED);
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