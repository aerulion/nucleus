package net.aerulion.nucleus.util;

import net.aerulion.nucleus.api.gui.GUI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryHolder;

public class GuiHandler implements Listener {
    @EventHandler
    public void onGuiClick(InventoryClickEvent event) {
        InventoryHolder inventoryHolder = event.getInventory().getHolder();
        if (inventoryHolder instanceof GUI) {
            event.setCancelled(true);
            GUI gui = (GUI) inventoryHolder;
            gui.handleClick(event);
        }
    }

    @EventHandler
    public void onGuiClose(InventoryCloseEvent event) {
        InventoryHolder inventoryHolder = event.getInventory().getHolder();
        if (inventoryHolder instanceof GUI) {
            GUI gui = (GUI) inventoryHolder;
            gui.handleClose(event);
        }
    }
}