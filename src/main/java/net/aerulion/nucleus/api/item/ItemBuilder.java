package net.aerulion.nucleus.api.item;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
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

/**
 * A class for creating ItemStacks
 *
 * @since 1.12
 */
public final class ItemBuilder {

  private final ItemStack itemStack;

  private ItemBuilder(@NotNull Material material, int amount) {
    itemStack = new ItemStack(material, amount);
  }

  public ItemBuilder(ItemStack itemStack) {
    this.itemStack = itemStack;
  }

  public static @NotNull ItemBuilder of(@NotNull Material material) {
    return new ItemBuilder(material, 1);
  }

  public static @NotNull ItemBuilder of(@NotNull Material material, int amount) {
    return new ItemBuilder(material, amount);
  }

  public static @NotNull ItemBuilder of(ItemStack itemStack) {
    return new ItemBuilder(itemStack);
  }

  public @NotNull ItemBuilder withMetaValue(@NotNull Consumer<@NotNull ItemMeta> itemMetaConsumer) {
    ItemMeta itemMeta = itemStack.getItemMeta();
    if (itemMeta != null) {
      itemMetaConsumer.accept(itemMeta);
    }
    itemStack.setItemMeta(itemMeta);
    return this;
  }

  public ItemStack build() {
    return itemStack;
  }

  public @NotNull ItemBuilder withEnchantments(@NotNull Map<Enchantment, Integer> enchantments) {
    itemStack.addEnchantments(enchantments);
    return this;
  }

  public @NotNull ItemBuilder withEnchantment(@NotNull Enchantment enchantment, int level) {
    itemStack.addEnchantment(enchantment, level);
    return this;
  }

  public @NotNull ItemBuilder withUnsafeEnchantments(
      @NotNull Map<Enchantment, Integer> enchantments) {
    itemStack.addUnsafeEnchantments(enchantments);
    return this;
  }

  public @NotNull ItemBuilder withUnsafeEnchantment(@NotNull Enchantment enchantment, int level) {
    itemStack.addUnsafeEnchantment(enchantment, level);
    return this;
  }

  public @NotNull ItemBuilder ofAmount(int amount) {
    itemStack.setAmount(amount);
    return this;
  }

  public @NotNull ItemBuilder withDisplayName(@Nullable Component name) {
    return withMetaValue(itemMeta -> itemMeta.displayName(name));
  }

  public @NotNull ItemBuilder withLocalizedName(@Nullable String name) {
    return withMetaValue(itemMeta -> itemMeta.setLocalizedName(name));
  }

  public @NotNull ItemBuilder withLore(@Nullable List<Component> lore) {
    return withMetaValue(itemMeta -> itemMeta.lore(lore));
  }

  public @NotNull ItemBuilder withLore(Component... lore) {
    return withLore(Arrays.asList(lore));
  }

  public @NotNull ItemBuilder withCustomModelData(@Nullable Integer data) {
    return withMetaValue(itemMeta -> itemMeta.setCustomModelData(data));
  }

  public @NotNull ItemBuilder withItemFlags(@NotNull ItemFlag... itemFlags) {
    return withMetaValue(itemMeta -> itemMeta.addItemFlags(itemFlags));
  }

  public @NotNull ItemBuilder withUnbreakable(boolean unbreakable) {
    return withMetaValue(itemMeta -> itemMeta.setUnbreakable(unbreakable));
  }

  public @NotNull ItemBuilder withAttributeModifier(@NotNull Attribute attribute,
      @NotNull AttributeModifier modifier) {
    return withMetaValue(itemMeta -> itemMeta.addAttributeModifier(attribute, modifier));
  }

  public @NotNull ItemBuilder withDurability(int damage) {
    return withMetaValue(itemMeta -> {
      if (itemMeta instanceof Damageable damageable) {
        damageable.setDamage(damage);
      }
    });
  }

  public @NotNull ItemBuilder withDyeColor(@NotNull Color color) {
    return withMetaValue(itemMeta -> {
      if (itemMeta instanceof LeatherArmorMeta leatherArmorMeta) {
        leatherArmorMeta.setColor(
            org.bukkit.Color.fromRGB(color.getRed(), color.getGreen(), color.getBlue()));
      }
    });
  }

  public @NotNull ItemBuilder withNBTData(
      @NotNull Consumer<@NotNull PersistentDataContainer> dataConsumer) {
    return withMetaValue(itemMeta -> dataConsumer.accept(itemMeta.getPersistentDataContainer()));
  }
}