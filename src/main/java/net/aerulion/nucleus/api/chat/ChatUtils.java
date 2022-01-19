package net.aerulion.nucleus.api.chat;

import lombok.experimental.UtilityClass;
import net.aerulion.nucleus.api.component.ComponentUtils;
import net.aerulion.nucleus.api.string.CenterPixel;
import net.aerulion.nucleus.api.string.StringUtils;
import net.kyori.adventure.audience.Audience;
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
  public static void sendCenteredChatMessage(final @NotNull Player player,
      final @Nullable String message) {
    player.sendMessage(StringUtils.generateCenteredString(message, CenterPixel.CHAT));
  }

  @Deprecated
  public static void sendCenteredChatMessage(final @NotNull CommandSender commandSender,
      final @Nullable String message) {
    commandSender.sendMessage(StringUtils.generateCenteredString(message, CenterPixel.CHAT));
  }

  /**
   * Sends a centered chat message to an audience
   *
   * @param audience The receiver of the message
   * @param message  The message to be sent
   */
  public static void sendCenteredChatMessage(final @NotNull Audience audience,
      final @NotNull Component message) {
    audience.sendMessage(ComponentUtils.generateCenteredComponent(message, CenterPixel.CHAT));
  }

  @Deprecated
  public static void sendChatDividingLine(final @NotNull Player player, final String chatColor) {
    player.sendMessage(chatColor + StringUtils.generateFullWidthLine(CenterPixel.CHAT));
  }

  @Deprecated
  public static void sendChatDividingLine(final @NotNull CommandSender commandSender,
      final String chatColor) {
    commandSender.sendMessage(chatColor + StringUtils.generateFullWidthLine(CenterPixel.CHAT));
  }

  @Deprecated
  public static void sendChatDividingLine(final @NotNull Player player,
      final @Nullable ChatColor chatColor) {
    player.sendMessage(chatColor + StringUtils.generateFullWidthLine(CenterPixel.CHAT));
  }

  /**
   * Sends a fullwidth line to an audience using the specified color
   *
   * @param audience  The receiver of the message
   * @param textColor The color of the line
   */
  public static void sendChatDividingLine(final @NotNull Audience audience,
      final @NotNull TextColor textColor) {
    audience.sendMessage(ComponentUtils.generateFullWidthLine(CenterPixel.CHAT).color(textColor));
  }
}