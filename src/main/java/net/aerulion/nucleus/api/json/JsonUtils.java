package net.aerulion.nucleus.api.json;

import lombok.experimental.UtilityClass;
import net.minecraft.nbt.CompoundTag;
import org.bukkit.craftbukkit.v1_18_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * A utility class for json related stuff
 */
@UtilityClass
public final class JsonUtils {

  /**
   * Converts a given ItemStack to raw json code
   *
   * @param itemStack The ItemStack to be converted
   * @return The raw json string
   */
  public static @NotNull String convertItemStackToJson(final @NotNull ItemStack itemStack) {
    final @NotNull net.minecraft.world.item.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(
        itemStack);
    final @NotNull CompoundTag compoundTag = new CompoundTag();
    nmsItemStack.save(compoundTag);
    return compoundTag.toString();
  }
}