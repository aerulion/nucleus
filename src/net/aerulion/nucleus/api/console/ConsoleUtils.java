package net.aerulion.nucleus.api.console;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

public class ConsoleUtils {
    public static void sendColoredConsoleMessage(final String msg) {
        final ConsoleCommandSender sender = Bukkit.getConsoleSender();
        sender.sendMessage(msg);
    }
}