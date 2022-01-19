package net.aerulion.nucleus.api.particle;

import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

/**
 * A utility class for particle related stuff
 */
@UtilityClass
public final class ParticleUtils {

  /**
   * Calculates a list of locations evenly spaced out around the center location
   *
   * @param center The center of the circle
   * @param radius The radius of the circle
   * @param steps  The amount of locations
   * @return an ArrayList of Locations
   */
  public static @NotNull List<Location> getCircle(final @NotNull Location center,
      final double radius, final int steps) {
    final World world = center.getWorld();
    final double increment = (2 * Math.PI) / steps;
    final @NotNull ArrayList<Location> locations = new ArrayList<>();
    for (int i = 0; i < steps; i++) {
      final double angle = i * increment;
      final double x = center.getX() + radius * Math.cos(angle);
      final double z = center.getZ() + radius * Math.sin(angle);
      locations.add(new Location(world, x, center.getY(), z));
    }
    return locations;
  }

  /**
   * Calculates a list of locations evenly spaced out around the center location in reverse order
   *
   * @param center The center of the circle
   * @param radius The radius of the circle
   * @param steps  The amount of locations
   * @return an ArrayList of Locations
   */
  public static @NotNull List<Location> getCircleReverse(final @NotNull Location center,
      final double radius, final int steps) {
    final World world = center.getWorld();
    final double increment = (2 * Math.PI) / steps;
    final @NotNull ArrayList<Location> locations = new ArrayList<>();
    for (int i = 0; i < steps; i++) {
      final double angle = i * increment;
      final double x = center.getX() - radius * Math.cos(angle);
      final double z = center.getZ() - radius * Math.sin(angle);
      locations.add(new Location(world, x, center.getY(), z));
    }
    return locations;
  }
}