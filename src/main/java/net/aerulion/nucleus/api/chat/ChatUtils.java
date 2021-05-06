package net.aerulion.nucleus.api.chat;

import lombok.experimental.UtilityClass;
import net.aerulion.nucleus.api.component.ComponentUtils;
import net.aerulion.nucleus.api.string.CenterPixel;
import net.aerulion.nucleus.api.string.StringUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A utility class for sending chat messages
 */
@UtilityClass
public final class ChatUtils {

    @Deprecated
    public static void sendCenteredChatMessage(@NotNull Player player, @Nullable String message) {
        player.sendMessage(StringUtils.generateCenteredString(message, CenterPixel.CHAT));
    }

    @Deprecated
    public static void sendCenteredChatMessage(@NotNull CommandSender commandSender, @Nullable String message) {
        commandSender.sendMessage(StringUtils.generateCenteredString(message, CenterPixel.CHAT));
    }

    @Deprecated
    public static void sendChatDividingLine(@NotNull Player player, String chatColor) {
        player.sendMessage(chatColor + StringUtils.generateFullWidthLine(CenterPixel.CHAT));
    }

    @Deprecated
    public static void sendChatDividingLine(@NotNull CommandSender commandSender, String chatColor) {
        commandSender.sendMessage(chatColor + StringUtils.generateFullWidthLine(CenterPixel.CHAT));
    }

    @Deprecated
    public static void sendChatDividingLine(@NotNull Player player, @Nullable ChatColor chatColor) {
        player.sendMessage(chatColor + StringUtils.generateFullWidthLine(CenterPixel.CHAT));
    }

    /**
     * Sends a centered chat message to an player
     *
     * @param player  The receiver of the message
     * @param message The message to be sent
     */
    public static void sendCenteredChatMessage(@NotNull Player player, @Nullable Component message) {
        player.sendMessage(ComponentUtils.generateCenteredComponent(message, CenterPixel.CHAT));
    }

    /**
     * Sends a centered chat message to an CommandSender
     *
     * @param commandSender The receiver of the message
     * @param message       The message to be sent
     */
    public static void sendCenteredChatMessage(@NotNull CommandSender commandSender, @Nullable Component message) {
        commandSender.sendMessage(ComponentUtils.generateCenteredComponent(message, CenterPixel.CHAT));
    }

    /**
     * Sends an fullwidth line to an player using the specified color
     *
     * @param player    The receiver of the message
     * @param textColor The color of the line
     */
    public static void sendChatDividingLine(@NotNull Player player, @Nullable TextColor textColor) {
        player.sendMessage(Component.text(StringUtils.generateFullWidthLine(CenterPixel.CHAT)).color(textColor));
    }

    /**
     * Sends an fullwidth line to an CommandSender using the specified color
     *
     * @param commandSender The receiver of the message
     * @param textColor     The color of the line
     */
    public static void sendChatDividingLine(@NotNull CommandSender commandSender, @Nullable TextColor textColor) {
        commandSender.sendMessage(Component.text(StringUtils.generateFullWidthLine(CenterPixel.CHAT)).color(textColor));
    }
}