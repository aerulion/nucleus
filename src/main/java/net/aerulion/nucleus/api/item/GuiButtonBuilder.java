package net.aerulion.nucleus.api.item;

import net.aerulion.nucleus.api.component.ComponentUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainComponentSerializer;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * A class for creating Gui Buttons
 *
 * @since 1.11
 */
public final class GuiButtonBuilder {
    private final ItemStack itemStack;
    private int padding;
    private boolean centered;

    private GuiButtonBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.padding = 2;
        this.centered = true;
    }

    /**
     * Creates a new GuiButtonBuilder
     *
     * @param itemStack The ItemStack used as a base
     * @return The GuiButtonBuilder instance
     */
    public static @NotNull GuiButtonBuilder of(ItemStack itemStack) {
        return new GuiButtonBuilder(itemStack);
    }

    /**
     * Creates a new GuiButtonBuilder
     *
     * @param material The Material used as a base
     * @return The GuiButtonBuilder instance
     */
    public static @NotNull GuiButtonBuilder of(@NotNull Material material) {
        return new GuiButtonBuilder(new ItemStack(material));
    }

    /**
     * Apply an ItemMeta consumer on the ItemMeta, if present
     *
     * @param itemMetaConsumer The Consumer used
     * @return The GuiButtonBuilder instance
     */
    public @NotNull GuiButtonBuilder withMetaValue(@NotNull Consumer<@NotNull ItemMeta> itemMetaConsumer) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta != null)
            itemMetaConsumer.accept(itemMeta);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    /**
     * Get the ItemStack
     *
     * @return ItemStack
     */
    public ItemStack build() {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta != null) {
            Component displayName = itemStack.getItemMeta().displayName();
            if (displayName == null) return itemStack;
            int pixelLength = ComponentUtils.getPixelLength(displayName);
            List<Component> lore = itemMeta.lore();
            if (lore == null) return itemStack;
            for (Component component : lore) {
                int length = ComponentUtils.getPixelLength(component);
                if (length > pixelLength)
                    pixelLength = length;
            }
            Component padding = Component.text(StringUtils.repeat(" ", this.padding))
                    .decoration(TextDecoration.BOLD, TextDecoration.State.FALSE)
                    .decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE);
            itemMeta.displayName(padding.append(
                    ComponentUtils.generateCenteredComponent(
                            Component.empty().decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE)
                                    .append(displayName), (int) Math.round(pixelLength / 2D)).append(padding)));
            List<Component> formattedLore = new ArrayList<>();
            for (Component component : lore) {
                if (PlainComponentSerializer.plain().serialize(component).equals("%divider"))
                    formattedLore.add(ComponentUtils.generateLine(pixelLength + this.padding * 8).color(component.color()));
                else
                    formattedLore.add(padding.append(
                            ComponentUtils.generateCenteredComponent(Component.empty()
                                    .decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE)
                                    .append(component), (int) Math.round(pixelLength / 2D))).append(padding));
            }
            itemMeta.lore(formattedLore);
            itemMeta.addItemFlags(ItemFlag.values());
            itemStack.setItemMeta(itemMeta);
        }
        return itemStack;
    }

    /**
     * Sets the amount of the ItemStack
     *
     * @param amount The new amount
     * @return The GuiButtonBuilder instance
     */
    public @NotNull GuiButtonBuilder ofAmount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    /**
     * Sets the display name of the ItemStack
     *
     * @param name The new name
     * @return The GuiButtonBuilder instance
     */
    public @NotNull GuiButtonBuilder withDisplayName(@Nullable Component name) {
        return withMetaValue(itemMeta -> itemMeta.displayName(name));
    }

    /**
     * Sets the lore of the ItemStack
     * If another lore is already present, it will get replaced
     *
     * @param lore The new lore
     * @return The GuiButtonBuilder instance
     */
    public @NotNull GuiButtonBuilder withLore(@Nullable List<Component> lore) {
        return withMetaValue(itemMeta -> itemMeta.lore(lore));
    }

    /**
     * Sets the lore of the ItemStack
     * If another lore is already present, it will get replaced
     *
     * @param lore The new lore
     * @return The GuiButtonBuilder instance
     */
    public @NotNull GuiButtonBuilder withLore(Component... lore) {
        return withLore(Arrays.asList(lore));
    }

    /**
     * Sets the CustomModelData of the ItemStack
     *
     * @param data The new CustomModelData
     * @return The GuiButtonBuilder instance
     */
    public @NotNull GuiButtonBuilder withCustomModelData(@Nullable Integer data) {
        return withMetaValue(itemMeta -> itemMeta.setCustomModelData(data));
    }

    /**
     * Sets the padding of the ItemStack
     * This value determines the amount of spaces added to the left and the right of the lore and display name
     * Default: 2
     *
     * @param padding The new padding value
     * @return The GuiButtonBuilder instance
     */
    public @NotNull GuiButtonBuilder withPadding(int padding) {
        this.padding = padding;
        return this;
    }

    /**
     * Toggles whether the lore and display name will be centered
     * Default: true
     *
     * @param bool The new centered value
     * @return The GuiButtonBuilder instance
     */
    public @NotNull GuiButtonBuilder withCentered(boolean bool) {
        this.centered = bool;
        return this;
    }

    /**
     * Enables the enchantment glint for the ItemStack
     *
     * @return The GuiButtonBuilder instance
     */
    public @NotNull GuiButtonBuilder withGlow() {
        return withMetaValue(itemMeta -> itemMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true));
    }

    public @NotNull GuiButtonBuilder withHideAttributes() {
        return withMetaValue(itemMeta -> itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES));
    }
}