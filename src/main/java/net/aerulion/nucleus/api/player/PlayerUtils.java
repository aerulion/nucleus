package net.aerulion.nucleus.api.player;

import net.aerulion.nucleus.exeptions.NotEnoughItemsException;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerUtils {

    public static boolean giveItemOrDrop(Player player, ItemStack itemStack) {
        HashMap<Integer, ItemStack> remainingItems = player.getInventory().addItem(itemStack);
        if (!remainingItems.isEmpty()) {
            for (ItemStack drop : remainingItems.values())
                player.getWorld().dropItemNaturally(player.getLocation(), drop);
            return true;
        }
        return false;
    }

    public static void takeItems(final Player PLAYER, final ItemStack ITEM, final int AMOUNT) throws NotEnoughItemsException {
        List<ItemStack> items = new ArrayList<>();
        int itemAmount = 0;
        for (ItemStack itemStack : PLAYER.getInventory()) {
            if (itemStack != null) {
                if (ITEM.isSimilar(itemStack)) {
                    items.add(itemStack);
                    itemAmount += itemStack.getAmount();
                    if (itemAmount >= AMOUNT)
                        break;
                }
            }
        }
        if (itemAmount >= AMOUNT) {
            itemAmount = 0;
            for (ItemStack itemStack : items) {
                if (itemAmount < AMOUNT) {
                    int amount = itemAmount + itemStack.getAmount() <= AMOUNT ? itemStack.getAmount() : (AMOUNT - itemAmount);
                    itemStack.setAmount(itemStack.getAmount() - amount);
                    itemAmount += amount;
                }
            }
        } else
            throw new NotEnoughItemsException();
    }
}