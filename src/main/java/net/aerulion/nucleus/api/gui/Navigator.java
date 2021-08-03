package net.aerulion.nucleus.api.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * The navigator class can be used to manage GUI routes and sub menus
 *
 * @since 1.17.0
 */
public class Navigator {

  private final @NotNull Map<Player, List<GUI>> guis = new HashMap<>();

  /**
   * Resumes the last gui
   *
   * @param player the specified player
   * @return true if successful
   */
  public boolean resume(@NotNull Player player) {
    if (guis.containsKey(player) && !guis.get(player).isEmpty()) {
      guis.get(player).get(guis.get(player).size() - 1).open();
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
    if (!guis.containsKey(player)) {
      return;
    }
    List<GUI> playerGuis = guis.get(player);
    if (playerGuis.isEmpty()) {
      guis.remove(player);
      return;
    }
    if (playerGuis.size() == 1) {
      player.closeInventory();
      guis.remove(player);
      return;
    }
    playerGuis.remove(playerGuis.size() - 1);
    playerGuis.get(playerGuis.size() - 1).open();
  }

  /**
   * Removes all guis from the stack
   *
   * @param player the specified player
   */
  public void popAll(@NotNull Player player) {
    if (!guis.containsKey(player)) {
      return;
    }
    player.closeInventory();
    guis.remove(player);
  }

  /**
   * Removes all guis from the stack and inserts a new one
   *
   * @param player the specified player
   * @param gui    the gui to add to the stack
   */
  public void popAllAndPush(@NotNull Player player, @NotNull GUI gui) {
    @NotNull List<GUI> playerGuis = new ArrayList<>();
    playerGuis.add(gui);
    gui.init();
    gui.open();
    guis.put(player, playerGuis);
  }

  /**
   * Pushes a new gui on top of the stack
   *
   * @param player the specified player
   * @param gui    the gui to add to the stack
   */
  public void push(@NotNull Player player, @NotNull GUI gui) {
    List<GUI> playerGuis = guis.getOrDefault(player, new ArrayList<>());
    playerGuis.add(gui);
    gui.init();
    gui.open();
    guis.put(player, playerGuis);
  }

  /**
   * Removes the topmost gui from the stack and pushes a new one on top
   *
   * @param player the specified player
   * @param gui    the gui to add to the stack
   */
  public void popAndPush(@NotNull Player player, @NotNull GUI gui) {
    List<GUI> playerGuis = guis.getOrDefault(player, new ArrayList<>());
    if (!playerGuis.isEmpty()) {
      playerGuis.remove(playerGuis.size() - 1);
    }
    playerGuis.add(gui);
    gui.init();
    gui.open();
    guis.put(player, playerGuis);
  }
}