package net.aerulion.nucleus.api.item;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import net.kyori.adventure.text.Component;
import org.bukkit.Color;
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

/**
 * A class for creating ItemStacks
 *
 * @since 1.12
 */
public final class ItemBuilder {

  private final ItemStack itemStack;

  private ItemBuilder(final @NotNull Material material, final int amount) {
    itemStack = new ItemStack(material, amount);
  }

  public ItemBuilder(final ItemStack itemStack) {
    this.itemStack = itemStack;
  }

  public static @NotNull ItemBuilder of(final @NotNull Material material) {
    return new ItemBuilder(material, 1);
  }

  public static @NotNull ItemBuilder of(final @NotNull Material material, final int amount) {
    return new ItemBuilder(material, amount);
  }

  public static @NotNull ItemBuilder of(final ItemStack itemStack) {
    return new ItemBuilder(itemStack);
  }

  public @NotNull ItemBuilder withMetaValue(
      final @NotNull Consumer<@NotNull ItemMeta> itemMetaConsumer) {
    final ItemMeta itemMeta = itemStack.getItemMeta();
    if (itemMeta != null) {
      itemMetaConsumer.accept(itemMeta);
    }
    itemStack.setItemMeta(itemMeta);
    return this;
  }

  public ItemStack build() {
    return itemStack;
  }

  public @NotNull ItemBuilder withEnchantments(
      final @NotNull Map<Enchantment, Integer> enchantments) {
    itemStack.addEnchantments(enchantments);
    return this;
  }

  public @NotNull ItemBuilder withEnchantment(final @NotNull Enchantment enchantment,
      final int level) {
    itemStack.addEnchantment(enchantment, level);
    return this;
  }

  public @NotNull ItemBuilder withUnsafeEnchantments(
      final @NotNull Map<Enchantment, Integer> enchantments) {
    itemStack.addUnsafeEnchantments(enchantments);
    return this;
  }

  public @NotNull ItemBuilder withUnsafeEnchantment(final @NotNull Enchantment enchantment,
      final int level) {
    itemStack.addUnsafeEnchantment(enchantment, level);
    return this;
  }

  public @NotNull ItemBuilder ofAmount(final int amount) {
    itemStack.setAmount(amount);
    return this;
  }

  public @NotNull ItemBuilder withDisplayName(final @Nullable Component name) {
    return withMetaValue(itemMeta -> itemMeta.displayName(name));
  }

  public @NotNull ItemBuilder withLocalizedName(final @Nullable String name) {
    return withMetaValue(itemMeta -> itemMeta.setLocalizedName(name));
  }

  public @NotNull ItemBuilder withLore(final @Nullable List<Component> lore) {
    return withMetaValue(itemMeta -> itemMeta.lore(lore));
  }

  public @NotNull ItemBuilder withLore(final Component... lore) {
    return withLore(Arrays.asList(lore));
  }

  public @NotNull ItemBuilder withCustomModelData(final @Nullable Integer data) {
    return withMetaValue(itemMeta -> itemMeta.setCustomModelData(data));
  }

  public @NotNull ItemBuilder withItemFlags(final @NotNull ItemFlag... itemFlags) {
    return withMetaValue(itemMeta -> itemMeta.addItemFlags(itemFlags));
  }

  public @NotNull ItemBuilder withUnbreakable(final boolean unbreakable) {
    return withMetaValue(itemMeta -> itemMeta.setUnbreakable(unbreakable));
  }

  public @NotNull ItemBuilder withAttributeModifier(final @NotNull Attribute attribute,
      final @NotNull AttributeModifier modifier) {
    return withMetaValue(itemMeta -> itemMeta.addAttributeModifier(attribute, modifier));
  }

  public @NotNull ItemBuilder withDurability(final int damage) {
    return withMetaValue(itemMeta -> {
      if (itemMeta instanceof Damageable damageable) {
        damageable.setDamage(damage);
      }
    });
  }

  public @NotNull ItemBuilder withDyeColor(final @NotNull Color color) {
    return withMetaValue(itemMeta -> {
      if (itemMeta instanceof LeatherArmorMeta leatherArmorMeta) {
        leatherArmorMeta.setColor(color);
      }
    });
  }

  public @NotNull ItemBuilder withNBTData(
      final @NotNull Consumer<@NotNull PersistentDataContainer> dataConsumer) {
    return withMetaValue(itemMeta -> dataConsumer.accept(itemMeta.getPersistentDataContainer()));
  }
}