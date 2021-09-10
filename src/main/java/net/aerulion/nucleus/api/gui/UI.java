package net.aerulion.nucleus.api.gui;

import net.aerulion.nucleus.api.item.GuiButtonBuilder;
import net.aerulion.nucleus.api.item.ItemBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * A collection of commonly used UI elements
 *
 * @since 1.18.0
 */
public enum UI {
    /**
     * A blank spacer for filling unused slots
     */
    SPACER(ItemBuilder
            .of(Material.BLACK_STAINED_GLASS_PANE)
            .withCustomModelData(1000)
            .withDisplayName(Component.empty())
            .build()),
    /**
     * A button with an arrow pointing upwards
     */
    BUTTON_ARROW_UP(ItemBuilder
            .of(Material.LIME_STAINED_GLASS_PANE)
            .withCustomModelData(1004)
            .withDisplayName(Component
                    .text("↑")
                    .color(NamedTextColor.GREEN)
                    .decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE))
            .build()),
    /**
     * A button with an arrow pointing downwards
     */
    BUTTON_ARROW_DOWN(ItemBuilder
            .of(Material.LIME_STAINED_GLASS_PANE)
            .withCustomModelData(1001)
            .withDisplayName(Component
                    .text("↓")
                    .color(NamedTextColor.GREEN)
                    .decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE))
            .build()),
    /**
     * A button with an arrow pointing leftwards
     */
    BUTTON_ARROW_LEFT(ItemBuilder
            .of(Material.LIME_STAINED_GLASS_PANE)
            .withCustomModelData(1002)
            .withDisplayName(Component
                    .text("←")
                    .color(NamedTextColor.GREEN)
                    .decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE))
            .build()),
    /**
     * A button with an arrow pointing rightwards
     */
    BUTTON_ARROW_RIGHT(ItemBuilder
            .of(Material.LIME_STAINED_GLASS_PANE)
            .withCustomModelData(1003)
            .withDisplayName(Component
                    .text("→")
                    .color(NamedTextColor.GREEN)
                    .decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE))
            .build()),
    /**
     * A button representing search input
     */
    BUTTON_SEARCH(GuiButtonBuilder
            .of(Material.BLUE_STAINED_GLASS_PANE)
            .withCustomModelData(1005)
            .withDisplayName(Component
                    .text("▶ Suchen...")
                    .color(NamedTextColor.BLUE)
                    .decorate(TextDecoration.BOLD))
            .withLore(
                    Component.text("%divider").color(NamedTextColor.GRAY),
                    Component.text("Linksklick:").color(NamedTextColor.GRAY),
                    Component.text("Suchen...").color(NamedTextColor.GRAY),
                    Component.text("Rechtsklick:").color(NamedTextColor.GRAY),
                    Component.text("Zurücksetzen").color(NamedTextColor.GRAY),
                    Component.text("%divider").color(NamedTextColor.GRAY))
            .build()),
    /**
     * A back button
     */
    BUTTON_BACK(ItemBuilder
            .of(Material.DARK_OAK_DOOR)
            .withCustomModelData(1000)
            .withDisplayName(Component
                    .text("Zurück")
                    .color(NamedTextColor.YELLOW)
                    .decorate(TextDecoration.BOLD)
                    .decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE))
            .build()),
    /**
     * A delete button
     */
    BUTTON_DELETE(ItemBuilder
            .of(Material.BARRIER)
            .withCustomModelData(100)
            .withDisplayName(Component
                    .text("Löschen")
                    .color(NamedTextColor.RED)
                    .decorate(TextDecoration.BOLD)
                    .decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE))
            .build()),
    /**
     * A black decoration element
     */
    DECORATION_BLACK(ItemBuilder
            .of(Material.BLACK_STAINED_GLASS_PANE)
            .withCustomModelData(999)
            .withDisplayName(Component.empty())
            .build()),
    /**
     * A blue decoration element
     */
    DECORATION_BLUE(ItemBuilder
            .of(Material.BLUE_STAINED_GLASS_PANE)
            .withCustomModelData(999)
            .withDisplayName(Component.empty())
            .build()),
    /**
     * A brown decoration element
     */
    DECORATION_BROWN(ItemBuilder
            .of(Material.BROWN_STAINED_GLASS_PANE)
            .withCustomModelData(999)
            .withDisplayName(Component.empty())
            .build()),
    /**
     * A cyan decoration element
     */
    DECORATION_CYAN(ItemBuilder
            .of(Material.CYAN_STAINED_GLASS_PANE)
            .withCustomModelData(999)
            .withDisplayName(Component.empty())
            .build()),
    /**
     * A gray decoration element
     */
    DECORATION_GRAY(ItemBuilder
            .of(Material.GRAY_STAINED_GLASS_PANE)
            .withCustomModelData(999)
            .withDisplayName(Component.empty())
            .build()),
    /**
     * A green decoration element
     */
    DECORATION_GREEN(ItemBuilder
            .of(Material.GREEN_STAINED_GLASS_PANE)
            .withCustomModelData(999)
            .withDisplayName(Component.empty())
            .build()),
    /**
     * A light blue decoration element
     */
    DECORATION_LIGHT_BLUE(ItemBuilder
            .of(Material.LIGHT_BLUE_STAINED_GLASS_PANE)
            .withCustomModelData(999)
            .withDisplayName(Component.empty())
            .build()),
    /**
     * A light gray decoration element
     */
    DECORATION_LIGHT_GRAY(ItemBuilder
            .of(Material.LIGHT_GRAY_STAINED_GLASS_PANE)
            .withCustomModelData(999)
            .withDisplayName(Component.empty())
            .build()),
    /**
     * A lime decoration element
     */
    DECORATION_LIME(ItemBuilder
            .of(Material.LIME_STAINED_GLASS_PANE)
            .withCustomModelData(999)
            .withDisplayName(Component.empty())
            .build()),
    /**
     * A magenta decoration element
     */
    DECORATION_MAGENTA(ItemBuilder
            .of(Material.MAGENTA_STAINED_GLASS_PANE)
            .withCustomModelData(999)
            .withDisplayName(Component.empty())
            .build()),
    /**
     * A orange decoration element
     */
    DECORATION_ORANGE(ItemBuilder
            .of(Material.ORANGE_STAINED_GLASS_PANE)
            .withCustomModelData(999)
            .withDisplayName(Component.empty())
            .build()),
    /**
     * A pink decoration element
     */
    DECORATION_PINK(ItemBuilder
            .of(Material.PINK_STAINED_GLASS_PANE)
            .withCustomModelData(999)
            .withDisplayName(Component.empty())
            .build()),
    /**
     * A purple decoration element
     */
    DECORATION_PURPLE(ItemBuilder
            .of(Material.PURPLE_STAINED_GLASS_PANE)
            .withCustomModelData(999)
            .withDisplayName(Component.empty())
            .build()),
    /**
     * A red decoration element
     */
    DECORATION_RED(ItemBuilder
            .of(Material.RED_STAINED_GLASS_PANE)
            .withCustomModelData(999)
            .withDisplayName(Component.empty())
            .build()),
    /**
     * A white decoration element
     */
    DECORATION_WHITE(ItemBuilder
            .of(Material.WHITE_STAINED_GLASS_PANE)
            .withCustomModelData(999)
            .withDisplayName(Component.empty())
            .build()),
    /**
     * A yellow decoration element
     */
    DECORATION_YELLOW(ItemBuilder
            .of(Material.YELLOW_STAINED_GLASS_PANE)
            .withCustomModelData(999)
            .withDisplayName(Component.empty())
            .build());

    private final ItemStack itemStack;

    UI(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Gets the ItemStack representing the UI element
     *
     * @return a clone of the ItemStack
     */
    public ItemStack get() {
        return itemStack.clone();
    }
}