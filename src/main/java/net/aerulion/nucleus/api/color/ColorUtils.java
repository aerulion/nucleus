package net.aerulion.nucleus.api.color;

import java.util.Objects;
import lombok.experimental.UtilityClass;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Color;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A utility class for handling color codes
 */
@UtilityClass
public final class ColorUtils {

  /**
   * Parses a given String to an ChatColor
   *
   * @param color The string to parse
   * @return The parsed ChatColor or ChatColor RESET if invalid
   */
  public static ChatColor parseColor(final @NotNull String color) {
    if (isValidColor(color)) {
      if (color.length() == 2) {
        return Objects.requireNonNull(org.bukkit.ChatColor.getByChar(color.substring(1)))
            .asBungee();
      }
      if (color.length() == 7) {
        return ChatColor.of(java.awt.Color.decode(color));
      }
    }
    return ChatColor.RESET;
  }

  /**
   * Checks whether the given string is a valid color code
   *
   * @param color The string to be checked
   * @return true if valid, false if invalid
   */
  public static boolean isValidColor(final @NotNull String color) {
    if (color.length() == 2 && color.startsWith("&") && ChatColor.ALL_CODES.contains(
        color.substring(1))) {
      final org.bukkit.@Nullable ChatColor chatColor = org.bukkit.ChatColor.getByChar(
          color.substring(1));
      if (chatColor != null) {
        return !chatColor.isFormat();
      }
    }
    return isValidHEXColor(color);
  }

  /**
   * Checks whether the given string is a valid hex color code
   *
   * @param color The string to be checked
   * @return true if valid, false if invalid
   */
  public static boolean isValidHEXColor(final @NotNull String color) {
    return color.length() == 7 && color.matches("^#([A-Fa-f0-9]{6})$");
  }

  /**
   * Parses a given HEX String to a bukkit color
   *
   * @param hex the string to parse
   * @return the bukkit color, or null if invalid
   */
  public static @Nullable Color colorFromHex(final @NotNull String hex) {
    if (!isValidHEXColor(hex)) {
      return null;
    } else {
      final int hexInt = Integer.decode("0x" + hex.substring(1));
      return Color.fromRGB(((hexInt & 0xFF0000) >> 16), ((hexInt & 0xFF00) >> 8), (hexInt & 0xFF));
    }
  }
}