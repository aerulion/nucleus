package net.aerulion.nucleus.api.particle;

import lombok.experimental.UtilityClass;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * A utility class for particle related stuff
 */
@UtilityClass
public final class ParticleUtils {

    /**
     * Calculates an list of locations evenly spaced out around the center location
     *
     * @param center The center of the circle
     * @param radius The radius of the circle
     * @param steps  The amount of locations
     * @return an ArrayList of Locations
     */
    public static @NotNull ArrayList<Location> getCircle(@NotNull Location center, @NotNull double radius, @NotNull int steps) {
        World world = center.getWorld();
        double increment = (2 * Math.PI) / steps;
        ArrayList<Location> locations = new ArrayList<>();
        for (int i = 0; i < steps; i++) {
            double angle = i * increment;
            double x = center.getX() + radius * Math.cos(angle);
            double z = center.getZ() + radius * Math.sin(angle);
            locations.add(new Location(world, x, center.getY(), z));
        }
        return locations;
    }

    /**
     * Calculates an list of locations evenly spaced out around the center location in reverse order
     *
     * @param center The center of the circle
     * @param radius The radius of the circle
     * @param steps  The amount of locations
     * @return an ArrayList of Locations
     */
    public static @NotNull ArrayList<Location> getCircleReverse(@NotNull Location center, @NotNull double radius, @NotNull int steps) {
        World world = center.getWorld();
        double increment = (2 * Math.PI) / steps;
        ArrayList<Location> locations = new ArrayList<>();
        for (int i = 0; i < steps; i++) {
            double angle = i * increment;
            double x = center.getX() - radius * Math.cos(angle);
            double z = center.getZ() - radius * Math.sin(angle);
            locations.add(new Location(world, x, center.getY(), z));
        }
        return locations;
    }
}