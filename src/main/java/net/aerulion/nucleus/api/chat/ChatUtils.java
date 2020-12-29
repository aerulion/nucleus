package net.aerulion.nucleus.api.chat;

import net.aerulion.nucleus.api.string.CenterPixel;
import net.aerulion.nucleus.api.string.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatUtils {

    public static void sendCenteredChatMessage(Player player, String message) {
        player.sendMessage(StringUtils.generateCenteredString(message, CenterPixel.CHAT));
    }

    public static void sendCenteredChatMessage(CommandSender commandSender, String message) {
        commandSender.sendMessage(StringUtils.generateCenteredString(message, CenterPixel.CHAT));
    }

    public static void sendChatDividingLine(Player player, String chatColor) {
        player.sendMessage(chatColor + StringUtils.generateFullWidthLine(CenterPixel.CHAT));
    }

    public static void sendChatDividingLine(CommandSender commandSender, String chatColor) {
        commandSender.sendMessage(chatColor + StringUtils.generateFullWidthLine(CenterPixel.CHAT));
    }
}