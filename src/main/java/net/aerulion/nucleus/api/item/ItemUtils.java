package net.aerulion.nucleus.api.item;

import java.util.ArrayList;
import java.util.List;
import net.aerulion.nucleus.api.string.StringUtils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

/**
 * These methods are deprecated and will be removed in a future release.
 */
public class ItemUtils {

  @Deprecated
  public static @NotNull ItemStack buildItemStack(final @NotNull ItemStack itemStack,
      final String displayName, final List<String> loreList, final boolean enchanted) {
    final ItemMeta itemMeta = itemStack.getItemMeta();
    if (itemMeta != null) {
      itemMeta.setDisplayName(displayName);
      itemMeta.setLore(loreList);
      if (enchanted) {
        itemMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
      }
    }
    itemStack.setItemMeta(itemMeta);
    return itemStack;
  }

  @Deprecated
  public static @NotNull ItemStack buildItemStack(final @NotNull Material material,
      final String displayName, final List<String> loreList, final boolean enchanted) {
    return buildItemStack(new ItemStack(material), displayName, loreList, enchanted);
  }

  @Deprecated
  public static @NotNull ItemStack buildGuiButton(final @NotNull ItemStack itemStack,
      String displayName, final @NotNull List<String> loreList, final boolean enchanted,
      final int padding) {
    int pixelLength = StringUtils.getPixelLength(displayName);
    for (final String string : loreList) {
      final int length = StringUtils.getPixelLength(string);
      if (length > pixelLength) {
        pixelLength = length;
      }
    }
    pixelLength += padding * 10;
    displayName =
        StringUtils.generateCenteredString(displayName, (int) Math.round(pixelLength / 2D))
            + org.apache.commons.lang.StringUtils.repeat("§r ", padding);
    final @NotNull List<String> formattedLore = new ArrayList<>();
    for (final @NotNull String string : loreList) {
      if (string.contains("%divider")) {
        formattedLore.add(string.replace("%divider",
            StringUtils.generateLine((int) Math.ceil(pixelLength / 4D))));
      } else {
        formattedLore.add(
            StringUtils.generateCenteredString(string, (int) Math.round(pixelLength / 2D))
                + org.apache.commons.lang.StringUtils.repeat("§r ", padding));
      }
    }
    return buildItemStack(itemStack, displayName, formattedLore, enchanted);
  }

  @Deprecated
  public static @NotNull ItemStack buildGuiButton(final @NotNull Material material,
      final String displayName, final @NotNull List<String> loreList, final boolean enchanted,
      final int padding) {
    return buildGuiButton(new ItemStack(material), displayName, loreList, enchanted, padding);
  }
}