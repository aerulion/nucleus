package net.aerulion.nucleus.util;

import net.aerulion.nucleus.api.gui.GUI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GuiHandler implements Listener {

  @EventHandler(priority = EventPriority.LOWEST)
  public void onGuiClick(@NotNull InventoryClickEvent event) {
    @Nullable InventoryHolder inventoryHolder = event.getInventory().getHolder();
    if (inventoryHolder instanceof GUI gui) {
      event.setCancelled(true);
      gui.handleClick(event);
    }
  }

  @EventHandler(priority = EventPriority.LOWEST)
  public void onGuiClose(@NotNull InventoryCloseEvent event) {
    @Nullable InventoryHolder inventoryHolder = event.getInventory().getHolder();
    if (inventoryHolder instanceof GUI gui) {
      gui.handleClose(event);
    }
  }
}