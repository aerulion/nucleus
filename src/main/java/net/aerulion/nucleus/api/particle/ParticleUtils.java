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
  public static @NotNull List<Location> getCircle(@NotNull Location center,
      @NotNull double radius, @NotNull int steps) {
    World world = center.getWorld();
    double increment = (2 * Math.PI) / steps;
    @NotNull ArrayList<Location> locations = new ArrayList<>();
    for (int i = 0; i < steps; i++) {
      double angle = i * increment;
      double x = center.getX() + radius * Math.cos(angle);
      double z = center.getZ() + radius * Math.sin(angle);
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
  public static @NotNull List<Location> getCircleReverse(@NotNull Location center,
      @NotNull double radius, @NotNull int steps) {
    World world = center.getWorld();
    double increment = (2 * Math.PI) / steps;
    @NotNull ArrayList<Location> locations = new ArrayList<>();
    for (int i = 0; i < steps; i++) {
      double angle = i * increment;
      double x = center.getX() - radius * Math.cos(angle);
      double z = center.getZ() - radius * Math.sin(angle);
      locations.add(new Location(world, x, center.getY(), z));
    }
    return locations;
  }
}