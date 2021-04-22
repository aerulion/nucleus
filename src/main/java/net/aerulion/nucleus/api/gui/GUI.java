package net.aerulion.nucleus.api.gui;

import net.aerulion.nucleus.api.component.ComponentUtils;
import net.aerulion.nucleus.api.item.ItemBuilder;
import net.aerulion.nucleus.api.string.CenterPixel;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

/**
 * An abstract class for creating custom chest guis
 *
 * @since 1.17.0
 */
public abstract class GUI implements InventoryHolder {

    protected Player player;
    protected final Inventory inventory;
    protected final ItemStack spacer = ItemBuilder.of(Material.BLACK_STAINED_GLASS_PANE).withDisplayName(Component.empty()).build();

    /**
     * Creates a new GUI instance and assigns it to the specified player
     *
     * @param player the specified player
     */
    public GUI(Player player) {
        this.inventory = Bukkit.createInventory(this, getSlots(), ComponentUtils.generateCenteredComponent(getTitle(), CenterPixel.INVENTORY_TITLE));
        this.player = player;
    }

    /**
     * The title of the gui
     *
     * @return the title component
     */
    public abstract Component getTitle();

    /**
     * The number of available slots
     *
     * @return the number of slots
     */
    public abstract int getSlots();

    /**
     * Sets the content of the gui
     */
    public abstract void setContent();

    /**
     * Handles the clicking
     *
     * @param event the redirected InventoryClickEvent
     */
    public abstract void handleClick(InventoryClickEvent event);

    /**
     * Handles the closing
     *
     * @param event the redirected InventoryCloseEvent
     */
    public abstract void handleClose(InventoryCloseEvent event);

    /**
     * Updates the contents and opens the gui to the player
     */
    public void open() {
        setContent();
        player.openInventory(inventory);
    }

    /**
     * Fills every unused slot with an black glass pane
     */
    public void fillSpacers() {
        fillSpacers(spacer);
    }

    /**
     * Fills every unused slot with the given itemStack
     *
     * @param itemStack the itemStack to use
     */
    public void fillSpacers(ItemStack itemStack) {
        for (int i = 0; i < getSlots(); i++) {
            if (inventory.getItem(i) == null)
                inventory.setItem(i, itemStack);
        }
    }

    /**
     * Override for InventoryHolder
     *
     * @return the gui inventory
     */
    @Override
    public Inventory getInventory() {
        return inventory;
    }
}