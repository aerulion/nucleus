package net.aerulion.nucleus.api.console;

import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * A utility class for sending console messages
 */
@UtilityClass
public final class ConsoleUtils {

  @Deprecated
  public static void sendColoredConsoleMessage(@NotNull String message) {
    final @NotNull ConsoleCommandSender sender = Bukkit.getConsoleSender();
    sender.sendMessage(message);
  }

  /**
   * Sends a colored message to the console
   *
   * @param message The message to be sent
   */
  public static void sendColoredConsoleMessage(@NotNull Component message) {
    final @NotNull ConsoleCommandSender sender = Bukkit.getConsoleSender();
    sender.sendMessage(message);
  }
}