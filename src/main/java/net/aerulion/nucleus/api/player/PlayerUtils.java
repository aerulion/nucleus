package net.aerulion.nucleus.api.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.experimental.UtilityClass;
import net.aerulion.nucleus.exeptions.NotEnoughItemsException;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A utility class for player related stuff
 */
@UtilityClass
public final class PlayerUtils {

  /**
   * Tries to add the given items to the players inventory, otherwise drops them on the ground
   *
   * @param player    The receiving player
   * @param itemStack The ItemStack to be given
   * @return true if items has been dropped
   */
  public static boolean giveItemOrDrop(final @NotNull Player player,
      final @NotNull ItemStack itemStack) {
    final @NotNull HashMap<Integer, ItemStack> remainingItems = player.getInventory()
        .addItem(itemStack);
    if (!remainingItems.isEmpty()) {
      for (final @NotNull ItemStack drop : remainingItems.values()) {
        player.getWorld().dropItemNaturally(player.getLocation(), drop);
      }
      return true;
    }
    return false;
  }

  /**
   * Tries to withdraw the specified item and amount from the players inventory
   *
   * @param player    The specified player
   * @param itemStack The item to be withdrawn
   * @param amount    The amount to be withdrawn
   * @throws NotEnoughItemsException if the player doesn't have enough item in his inventory
   */
  public static void takeItems(final @NotNull Player player, final @NotNull ItemStack itemStack,
      final int amount) throws NotEnoughItemsException {
    final @NotNull List<ItemStack> items = new ArrayList<>();
    int itemAmount = 0;
    for (final @Nullable ItemStack stack : player.getInventory()) {
      if (stack != null && itemStack.isSimilar(stack)) {
        items.add(stack);
        itemAmount += stack.getAmount();
        if (itemAmount >= amount) {
          break;
        }
      }
    }
    if (itemAmount >= amount) {
      itemAmount = 0;
      for (final @NotNull ItemStack stack : items) {
        if (itemAmount < amount) {
          final int amount2 =
              itemAmount + stack.getAmount() <= amount ? stack.getAmount() : (amount - itemAmount);
          stack.setAmount(stack.getAmount() - amount2);
          itemAmount += amount2;
        }
      }
    } else {
      throw new NotEnoughItemsException();
    }
  }
}