package net.aerulion.nucleus.api.nbt;

import lombok.experimental.UtilityClass;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * A utility class for NBT related stuff
 */
@UtilityClass
public final class NbtUtils {

    /**
     * Adds an NBT tag to the given ItemStack
     *
     * @param itemStack The original ItemStack
     * @param key       The key of the NBT tag
     * @param value     The value of the NBT tag
     * @return The ItemStack with the NBT tag applied
     */
    public static ItemStack setNBTString(@NotNull ItemStack itemStack, @NotNull String key, @NotNull String value) {
        net.minecraft.server.v1_16_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound localNBTTagCompound = nmsItemStack.getOrCreateTag();
        localNBTTagCompound.setString(key, value);
        nmsItemStack.setTag(localNBTTagCompound);
        return CraftItemStack.asBukkitCopy(nmsItemStack);
    }

    /**
     * Gets the value of an NBT tag using a given key
     *
     * @param itemStack The given ItemStack
     * @param key       The specified key
     * @return The value associated with the given key or an empty String, if the key doesn't exist
     */
    public static String getNBTString(@NotNull ItemStack itemStack, @NotNull String key) {
        net.minecraft.server.v1_16_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound localNBTTagCompound = nmsItemStack.getTag();
        if ((localNBTTagCompound != null) && (localNBTTagCompound.hasKey(key)))
            return localNBTTagCompound.getString(key);
        return "";
    }

    /**
     * Adds an NBT tag to the given ItemStack
     *
     * @param itemStack The original ItemStack
     * @param key       The key of the NBT tag
     * @param value     The value of the NBT tag
     * @return The ItemStack with the NBT tag applied
     */
    public static ItemStack setNBTInt(@NotNull ItemStack itemStack, @NotNull String key, @NotNull int value) {
        net.minecraft.server.v1_16_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound localNBTTagCompound = nmsItemStack.getOrCreateTag();
        localNBTTagCompound.setInt(key, value);
        nmsItemStack.setTag(localNBTTagCompound);
        return CraftItemStack.asBukkitCopy(nmsItemStack);
    }

    /**
     * Gets the value of an NBT tag using a given key
     *
     * @param itemStack The given ItemStack
     * @param key       The specified key
     * @return The value associated with the given key or 0, if the key doesn't exist
     */
    public static int getNBTInt(@NotNull ItemStack itemStack, @NotNull String key) {
        net.minecraft.server.v1_16_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound localNBTTagCompound = nmsItemStack.getTag();
        if ((localNBTTagCompound != null) && (localNBTTagCompound.hasKey(key)))
            return localNBTTagCompound.getInt(key);
        return 0;
    }

    /**
     * Adds an NBT tag to the given ItemStack
     *
     * @param itemStack The original ItemStack
     * @param key       The key of the NBT tag
     * @param value     The value of the NBT tag
     * @return The ItemStack with the NBT tag applied
     */
    public static ItemStack setNBTBoolean(@NotNull ItemStack itemStack, @NotNull String key, @NotNull boolean value) {
        net.minecraft.server.v1_16_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound NBTTagCompound = nmsItemStack.getOrCreateTag();
        NBTTagCompound.setBoolean(key, value);
        nmsItemStack.setTag(NBTTagCompound);
        return CraftItemStack.asBukkitCopy(nmsItemStack);
    }

    /**
     * Gets the value of an NBT tag using a given key
     *
     * @param itemStack The given ItemStack
     * @param key       The specified key
     * @return The value associated with the given key or 'false', if the key doesn't exist
     */
    public static boolean getNBTBoolean(@NotNull ItemStack itemStack, @NotNull String key) {
        net.minecraft.server.v1_16_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound NBTTagCompound = nmsItemStack.getTag();
        return ((NBTTagCompound != null) && (NBTTagCompound.hasKey(key))) && NBTTagCompound.getBoolean(key);
    }

    /**
     * Adds an NBT tag compound to the given ItemStack
     *
     * @param itemStack The original ItemStack
     * @param key       The key of the NBT compound
     * @param values    A Map representing key and value of the new compound
     * @return The ItemStack with the NBT compound applied
     */
    public static ItemStack setNBTIntCompound(@NotNull ItemStack itemStack, @NotNull String key, @NotNull HashMap<String, Integer> values) {
        net.minecraft.server.v1_16_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound nbtTagCompound = nmsItemStack.getOrCreateTag();
        NBTTagCompound nbtTagCompound2 = new NBTTagCompound();
        values.forEach(nbtTagCompound2::setInt);
        nbtTagCompound.set(key, nbtTagCompound2);
        nmsItemStack.setTag(nbtTagCompound);
        return CraftItemStack.asBukkitCopy(nmsItemStack);
    }

    /**
     * Gets the value of an NBT compound using a given key
     *
     * @param itemStack The given ItemStack
     * @param key       The specified key
     * @return A Map containing the key value pairs or null, if the compound key doesn't exist
     */
    public static HashMap<String, Integer> getNBTIntCompound(@NotNull ItemStack itemStack, @NotNull String key) {
        net.minecraft.server.v1_16_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound nbtTagCompound = nmsItemStack.getTag();
        if (nbtTagCompound != null && nbtTagCompound.hasKey(key)) {
            HashMap<String, Integer> values = new HashMap<>();
            nbtTagCompound.getCompound(key).getKeys().forEach(s -> values.put(s, nbtTagCompound.getCompound(key).getInt(s)));
            return values;
        }
        return null;
    }
}