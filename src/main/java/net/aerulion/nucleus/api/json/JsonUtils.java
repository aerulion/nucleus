package net.aerulion.nucleus.api.json;

import lombok.experimental.UtilityClass;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * A utility class for json related stuff
 */
@UtilityClass
public final class JsonUtils {

    /**
     * Converts an given ItemStack to an raw json code
     *
     * @param itemStack The ItemStack to be converted
     * @return The raw json string
     */
    public static String convertItemStackToJson(@NotNull ItemStack itemStack) {
        net.minecraft.server.v1_16_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound compound = new NBTTagCompound();
        nmsItemStack.save(compound);
        return compound.toString();
    }
}