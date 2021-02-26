package net.aerulion.nucleus.api.item;

import net.aerulion.nucleus.api.string.StringUtils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * These methods are deprecated and will be removed in an future release.
 */
public class ItemUtils {
    @Deprecated
    public static ItemStack buildItemStack(ItemStack itemStack, String displayName, List<String> loreList, boolean enchanted) {
        ItemMeta itemMeta = itemStack.getItemMeta();
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
    public static ItemStack buildItemStack(Material material, String displayName, List<String> loreList, boolean enchanted) {
        return buildItemStack(new ItemStack(material), displayName, loreList, enchanted);
    }

    @Deprecated
    public static ItemStack buildGuiButton(ItemStack itemStack, String displayName, List<String> loreList, boolean enchanted, int padding) {
        int pixelLength = StringUtils.getPixelLength(displayName);
        for (String string : loreList) {
            int length = StringUtils.getPixelLength(string);
            if (length > pixelLength)
                pixelLength = length;
        }
        pixelLength += padding * 10;
        displayName = StringUtils.generateCenteredString(displayName, (int) Math.round(pixelLength / 2D)) + org.apache.commons.lang.StringUtils.repeat("§r ", padding);
        List<String> formattedLore = new ArrayList<>();
        for (String string : loreList) {
            if (string.contains("%divider")) {
                formattedLore.add(string.replace("%divider", StringUtils.generateLine((int) Math.ceil((double) pixelLength / 4D))));
            } else {
                formattedLore.add(StringUtils.generateCenteredString(string, (int) Math.round(pixelLength / 2D)) + org.apache.commons.lang.StringUtils.repeat("§r ", padding));
            }
        }
        return buildItemStack(itemStack, displayName, formattedLore, enchanted);
    }

    @Deprecated
    public static ItemStack buildGuiButton(Material material, String displayName, List<String> loreList, boolean enchanted, int padding) {
        return buildGuiButton(new ItemStack(material), displayName, loreList, enchanted, padding);
    }
}