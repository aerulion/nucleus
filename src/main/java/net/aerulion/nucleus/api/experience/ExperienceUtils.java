package net.aerulion.nucleus.api.experience;

import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * A utility class for handling experience
 */
@UtilityClass
public final class ExperienceUtils {

  /**
   * Gets the total experience needed to reach the specified level
   *
   * @param level The level to reach
   * @return The amount of experience points needed to reach the specified level
   */
  public static int getTotalExperience(int level) {
    int xp = 0;
    if (level >= 0 && level <= 15) {
      xp = (int) Math.ceil(Math.pow(level, 2D) + 6 * level);
    } else if (level > 15 && level <= 30) {
      xp = (int) Math.ceil((2.5 * Math.pow(level, 2D) - 40.5 * level + 360));
    } else if (level > 30) {
      xp = (int) Math.ceil((4.5 * Math.pow(level, 2D) - 162.5 * level + 2220));
    }
    return xp;
  }

  /**
   * Gets the total experience of a player
   *
   * @param player The player to check
   * @return The total amount of experience points of the given player
   */
  public static int getTotalExperience(@NotNull Player player) {
    return (int) (Math.floor(player.getExp() * player.getExpToLevel()) + getTotalExperienceFloored(
        player.getLevel()));
  }

  private static int getTotalExperienceFloored(int level) {
    int xp = 0;
    if (level >= 0 && level <= 15) {
      xp = (int) Math.floor(Math.pow(level, 2D) + 6 * level);
    } else if (level > 15 && level <= 30) {
      xp = (int) Math.floor((2.5 * Math.pow(level, 2D) - 40.5 * level + 360));
    } else if (level > 30) {
      xp = (int) Math.floor((4.5 * Math.pow(level, 2D) - 162.5 * level + 2220));
    }
    return xp;
  }

  /**
   * Calculates the level equivalent of the given amount of experience points
   *
   * @param amount The amount of experience points
   * @return The reachable level with the given amount of experience points
   */
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

  /**
   * Sets the total experience of a player
   *
   * @param player The specified player
   * @param amount The amount of experience points
   */
  public static void setTotalExperience(@NotNull Player player, int amount) {
    int level = getLevelEquivalent(amount);
    int xp = amount - getTotalExperience(level);
    player.setLevel(level);
    player.setExp(0);
    player.giveExp(xp);
  }
}