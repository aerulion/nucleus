package net.aerulion.nucleus.api.nbt;

import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class NbtUtils {
    public static ItemStack setNBTString(ItemStack item, String key, String value) {
        net.minecraft.server.v1_16_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound localNBTTagCompound = nmsItemStack.getTag();
        if (localNBTTagCompound == null)
            localNBTTagCompound = new NBTTagCompound();
        localNBTTagCompound.setString(key, value);
        nmsItemStack.setTag(localNBTTagCompound);
        return CraftItemStack.asBukkitCopy(nmsItemStack);
    }

    public static String getNBTString(ItemStack item, String key) {
        net.minecraft.server.v1_16_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound localNBTTagCompound = nmsItemStack.getTag();
        if ((localNBTTagCompound != null) && (localNBTTagCompound.hasKey(key)))
            return localNBTTagCompound.getString(key);
        return "";
    }

    public static ItemStack setNBTInt(ItemStack item, String key, int value) {
        net.minecraft.server.v1_16_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound localNBTTagCompound = nmsItemStack.getTag();
        if (localNBTTagCompound == null)
            localNBTTagCompound = new NBTTagCompound();
        localNBTTagCompound.setInt(key, value);
        nmsItemStack.setTag(localNBTTagCompound);
        return CraftItemStack.asBukkitCopy(nmsItemStack);
    }

    public static int getNBTInt(ItemStack item, String key) {
        net.minecraft.server.v1_16_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound localNBTTagCompound = nmsItemStack.getTag();
        if ((localNBTTagCompound != null) && (localNBTTagCompound.hasKey(key)))
            return localNBTTagCompound.getInt(key);
        return 0;
    }

    public static ItemStack setNBTBoolean(ItemStack item, String key, boolean value) {
        net.minecraft.server.v1_16_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound NBTTagCompound = nmsItemStack.getTag();
        if (NBTTagCompound == null)
            NBTTagCompound = new NBTTagCompound();
        NBTTagCompound.setBoolean(key, value);
        nmsItemStack.setTag(NBTTagCompound);
        return CraftItemStack.asBukkitCopy(nmsItemStack);
    }

    public static boolean getNBTBoolean(ItemStack item, String key) {
        net.minecraft.server.v1_16_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound NBTTagCompound = nmsItemStack.getTag();
        return ((NBTTagCompound != null) && (NBTTagCompound.hasKey(key))) && NBTTagCompound.getBoolean(key);
    }
}