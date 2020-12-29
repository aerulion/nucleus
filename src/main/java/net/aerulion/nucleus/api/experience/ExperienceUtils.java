package net.aerulion.nucleus.api.experience;

import org.bukkit.entity.Player;

public class ExperienceUtils {

    public static int getTotalExperience(int level) {
        int xp = 0;
        if (level >= 0 && level <= 15)
            xp = Math.toIntExact(Math.round(Math.pow(level, 2) + 6 * level));
        else if (level > 15 && level <= 30)
            xp = Math.toIntExact(Math.round((2.5 * Math.pow(level, 2) - 40.5 * level + 360)));
        else if (level > 30)
            xp = Math.toIntExact(Math.round(((4.5 * Math.pow(level, 2) - 162.5 * level + 2220))));
        return xp;
    }

    public static int getTotalExperience(Player player) {
        return Math.round(player.getExp() * player.getExpToLevel()) + getTotalExperience(player.getLevel());
    }

    public static int getLevelEquivalent(int amount) {
        float a = 0;
        float b = 0;
        float c = -amount;
        if (amount > getTotalExperience(0) && amount <= getTotalExperience(15)) {
            a = 1;
            b = 6;
        } else if (amount > getTotalExperience(15) && amount <= getTotalExperience(30)) {
            a = 2.5f;
            b = -40.5f;
            c += 360;
        } else if (amount > getTotalExperience(30)) {
            a = 4.5f;
            b = -162.5f;
            c += 2220;
        }
        return (int) Math.floor((-b + Math.sqrt(Math.pow(b, 2) - (4 * a * c))) / (2 * a));
    }

    public static void setTotalExperience(Player player, int amount) {
        int level = getLevelEquivalent(amount);
        int xp = amount - getTotalExperience(level);
        player.setLevel(level);
        player.setExp(0);
        player.giveExp(xp);
    }
}