package net.aerulion.nucleus.api.item;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * A class for creating ItemStacks
 *
 * @since 1.12
 */
public final class ItemBuilder {

    private final ItemStack itemStack;

    private ItemBuilder(Material material, int amount) {
        itemStack = new ItemStack(material, amount);
    }

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public static ItemBuilder of(Material material) {
        return new ItemBuilder(material, 1);
    }

    public static ItemBuilder of(Material material, int amount) {
        return new ItemBuilder(material, amount);
    }

    public static ItemBuilder of(ItemStack itemStack) {
        return new ItemBuilder(itemStack);
    }

    public ItemBuilder withMetaValue(Consumer<@NotNull ItemMeta> itemMetaConsumer) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta != null)
            itemMetaConsumer.accept(itemMeta);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemStack build() {
        return itemStack;
    }

    public ItemBuilder withEnchantments(@NotNull Map<Enchantment, Integer> enchantments) {
        itemStack.addEnchantments(enchantments);
        return this;
    }

    public ItemBuilder withEnchantment(@NotNull Enchantment enchantment, int level) {
        itemStack.addEnchantment(enchantment, level);
        return this;
    }

    public ItemBuilder withUnsafeEnchantments(@NotNull Map<Enchantment, Integer> enchantments) {
        itemStack.addUnsafeEnchantments(enchantments);
        return this;
    }

    public ItemBuilder withUnsafeEnchantment(@NotNull Enchantment enchantment, int level) {
        itemStack.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public ItemBuilder ofAmount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder withDisplayName(@Nullable Component name) {
        return withMetaValue(itemMeta -> itemMeta.displayName(name));
    }

    public ItemBuilder withLocalizedName(@Nullable String name) {
        return withMetaValue(itemMeta -> itemMeta.setLocalizedName(name));
    }

    public ItemBuilder withLore(@Nullable List<Component> lore) {
        return withMetaValue(itemMeta -> itemMeta.lore(lore));
    }

    public ItemBuilder withLore(Component... lore) {
        return withLore(Arrays.asList(lore));
    }

    public ItemBuilder withCustomModelData(@Nullable Integer data) {
        return withMetaValue(itemMeta -> itemMeta.setCustomModelData(data));
    }

    public ItemBuilder withItemFlags(@NotNull ItemFlag... itemFlags) {
        return withMetaValue(itemMeta -> itemMeta.addItemFlags(itemFlags));
    }

    public ItemBuilder withUnbreakable(boolean unbreakable) {
        return withMetaValue(itemMeta -> itemMeta.setUnbreakable(unbreakable));
    }

    public ItemBuilder withAttributeModifier(@NotNull Attribute attribute, @NotNull AttributeModifier modifier) {
        return withMetaValue(itemMeta -> itemMeta.addAttributeModifier(attribute, modifier));
    }

    public ItemBuilder withDurability(int damage) {
        return withMetaValue(itemMeta -> {
            if (itemMeta instanceof Damageable)
                ((Damageable) itemMeta).setDamage(damage);
        });
    }

    public ItemBuilder withDyeColor(Color color) {
        return withMetaValue(itemMeta -> {
            if (itemMeta instanceof LeatherArmorMeta)
                ((LeatherArmorMeta) itemMeta).setColor(org.bukkit.Color.fromRGB(color.getRed(), color.getGreen(), color.getBlue()));
        });
    }

    public ItemBuilder withNBTData(Consumer<@NotNull PersistentDataContainer> dataConsumer) {
        return withMetaValue(itemMeta -> dataConsumer.accept(itemMeta.getPersistentDataContainer()));
    }
}