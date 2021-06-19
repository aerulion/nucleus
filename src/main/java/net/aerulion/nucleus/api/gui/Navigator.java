package net.aerulion.nucleus.api.gui;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The navigator class can be used to manage GUI routes and sub menus
 *
 * @since 1.17.0
 */
public class Navigator {

    private final @NotNull HashMap<Player, List<GUI>> GUIS = new HashMap<>();

    /**
     * Resumes the last gui
     *
     * @param player the specified player
     * @return true if successful
     */
    public boolean resume(@NotNull Player player) {
        if (GUIS.containsKey(player) && !GUIS.get(player).isEmpty()) {
            GUIS.get(player).get(GUIS.get(player).size() - 1).open();
            return true;
        }
        return false;
    }

    /**
     * Removes the topmost gui from the stack
     *
     * @param player the specified player
     */
    public void pop(@NotNull Player player) {
        if (!GUIS.containsKey(player)) return;
        List<GUI> guis = GUIS.get(player);
        if (guis.isEmpty()) {
            GUIS.remove(player);
            return;
        }
        if (guis.size() == 1) {
            player.closeInventory();
            GUIS.remove(player);
            return;
        }
        guis.remove(guis.size() - 1);
        guis.get(guis.size() - 1).open();
    }

    /**
     * Removes all guis from the stack
     *
     * @param player the specified player
     */
    public void popAll(@NotNull Player player) {
        if (!GUIS.containsKey(player)) return;
        player.closeInventory();
        GUIS.remove(player);
    }

    /**
     * Removes all guis from the stack and inserts a new one
     *
     * @param player the specified player
     * @param gui    the gui to add to the stack
     */
    public void popAllAndPush(@NotNull Player player, @NotNull GUI gui) {
        @NotNull List<GUI> guis = new ArrayList<>();
        guis.add(gui);
        gui.init();
        gui.open();
        GUIS.put(player, guis);
    }

    /**
     * Pushes a new gui on top of the stack
     *
     * @param player the specified player
     * @param gui    the gui to add to the stack
     */
    public void push(@NotNull Player player, @NotNull GUI gui) {
        List<GUI> guis = GUIS.getOrDefault(player, new ArrayList<>());
        guis.add(gui);
        gui.init();
        gui.open();
        GUIS.put(player, guis);
    }

    /**
     * Removes the topmost gui from the stack and pushes a new one on top
     *
     * @param player the specified player
     * @param gui    the gui to add to the stack
     */
    public void popAndPush(@NotNull Player player, @NotNull GUI gui) {
        List<GUI> guis = GUIS.getOrDefault(player, new ArrayList<>());
        if (!guis.isEmpty())
            guis.remove(guis.size() - 1);
        guis.add(gui);
        gui.init();
        gui.open();
        GUIS.put(player, guis);
    }
}