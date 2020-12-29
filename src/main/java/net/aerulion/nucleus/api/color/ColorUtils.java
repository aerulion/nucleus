package net.aerulion.nucleus.api.color;

import net.md_5.bungee.api.ChatColor;

import java.awt.*;

public class ColorUtils {

    public static ChatColor parseColor(String color) {
        if (isValidColor(color)) {
            if (color.length() == 2)
                return org.bukkit.ChatColor.getByChar(color.substring(1)).asBungee();
            if (color.length() == 7)
                return ChatColor.of(Color.decode(color));
        }
        return ChatColor.RESET;
    }

    public static boolean isValidColor(String color) {
        if (color.length() == 2 && color.startsWith("&") && ChatColor.ALL_CODES.contains(color.substring(1))) {
            org.bukkit.ChatColor chatColor = org.bukkit.ChatColor.getByChar(color.substring(1));
            if (chatColor != null) {
                return !chatColor.isFormat();
            }
        }
        return color.length() == 7 && color.matches("^#([A-Fa-f0-9]{6})$");
    }
}