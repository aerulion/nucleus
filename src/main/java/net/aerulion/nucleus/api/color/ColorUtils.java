package net.aerulion.nucleus.api.color;

import lombok.experimental.UtilityClass;
import net.md_5.bungee.api.ChatColor;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Objects;

/**
 * A utility class for handling color codes
 */
@UtilityClass
public final class ColorUtils {

    /**
     * Parses an given String to to an ChatColor
     *
     * @param color The string to parse
     * @return The parsed ChatColor or ChatColor.RESET if invalid
     */
    public static ChatColor parseColor(@NotNull String color) {
        if (isValidColor(color)) {
            if (color.length() == 2)
                return Objects.requireNonNull(org.bukkit.ChatColor.getByChar(color.substring(1))).asBungee();
            if (color.length() == 7)
                return ChatColor.of(Color.decode(color));
        }
        return ChatColor.RESET;
    }

    /**
     * Checks whether the given string is an valid color code
     *
     * @param color The string to be checked
     * @return true if valid, false if invalid
     */
    public static boolean isValidColor(@NotNull String color) {
        if (color.length() == 2 && color.startsWith("&") && ChatColor.ALL_CODES.contains(color.substring(1))) {
            org.bukkit.ChatColor chatColor = org.bukkit.ChatColor.getByChar(color.substring(1));
            if (chatColor != null) {
                return !chatColor.isFormat();
            }
        }
        return isValidHEXColor(color);
    }

    /**
     * Checks whether the given string is an valid hex color code
     *
     * @param color The string to be checked
     * @return true if valid, false if invalid
     */
    public static boolean isValidHEXColor(@NotNull String color) {
        return color.length() == 7 && color.matches("^#([A-Fa-f0-9]{6})$");
    }
}