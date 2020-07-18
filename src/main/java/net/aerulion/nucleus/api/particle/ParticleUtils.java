package net.aerulion.nucleus.api.particle;

import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;

public class ParticleUtils {
    public static ArrayList<Location> getCircle(Location center, double radius, int steps) {
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

    public static ArrayList<Location> getCircleReverse(Location center, double radius, int steps) {
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