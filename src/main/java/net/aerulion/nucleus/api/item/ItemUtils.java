package net.aerulion.nucleus.api.item;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemUtils {
    public static ItemStack buildItemStack(ItemStack itemStack, String displayName, List<String> loreList, boolean enchanted) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(loreList);
        if (enchanted) {
            itemMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack buildItemStack(Material material, String displayName, List<String> loreList, boolean enchanted) {
        return buildItemStack(new ItemStack(material), displayName, loreList, enchanted);
    }
}