package net.aerulion.nucleus.api.json;

import lombok.experimental.UtilityClass;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
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
  public static String convertItemStackToJson(@NotNull ItemStack itemStack) {
    net.minecraft.world.item.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
    @NotNull NBTTagCompound compound = new NBTTagCompound();
    nmsItemStack.save(compound);
    return compound.toString();
  }
}