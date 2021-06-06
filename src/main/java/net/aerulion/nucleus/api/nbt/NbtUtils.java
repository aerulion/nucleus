package net.aerulion.nucleus.api.nbt;

import lombok.experimental.UtilityClass;
import net.aerulion.nucleus.api.nbt.tag.*;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A utility class for NBT related stuff
 */
@UtilityClass
public final class NbtUtils {

    /**
     * Gets the current nbt tags of an ItemStack
     *
     * @param itemStack the item
     * @return Map of NbtTags
     */
    public static @NotNull HashMap<String, NbtTag> getTags(@NotNull ItemStack itemStack) {
        return getTagsFromCompound(CraftItemStack.asNMSCopy(itemStack).getTag());
    }

    /**
     * Sets the nbt tags of an ItemStack
     *
     * @param itemStack the item
     * @param nbtTags   the map of nbt tags
     * @return the modified item
     */
    public static @NotNull ItemStack setTags(@NotNull ItemStack itemStack, @NotNull HashMap<String, NbtTag> nbtTags) {
        net.minecraft.server.v1_16_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack.clone());
        nmsItemStack.setTag(createNBTTagCompound(nbtTags));
        return CraftItemStack.asBukkitCopy(nmsItemStack);
    }

    private static NBTTagCompound createNBTTagCompound(HashMap<String, NbtTag> nbtTags) {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        for (NbtTag nbtTag : nbtTags.values()) {
            if (nbtTag instanceof ByteNbtTag)
                nbtTagCompound.set(nbtTag.getKey(), NBTTagByte.a(((ByteNbtTag) nbtTag).getValue()));
            else if (nbtTag instanceof ShortNbtTag)
                nbtTagCompound.set(nbtTag.getKey(), NBTTagShort.a(((ShortNbtTag) nbtTag).getValue()));
            else if (nbtTag instanceof IntNbtTag)
                nbtTagCompound.set(nbtTag.getKey(), NBTTagInt.a(((IntNbtTag) nbtTag).getValue()));
            else if (nbtTag instanceof LongNbtTag)
                nbtTagCompound.set(nbtTag.getKey(), NBTTagLong.a(((LongNbtTag) nbtTag).getValue()));
            else if (nbtTag instanceof FloatNbtTag)
                nbtTagCompound.set(nbtTag.getKey(), NBTTagFloat.a(((FloatNbtTag) nbtTag).getValue()));
            else if (nbtTag instanceof DoubleNbtTag)
                nbtTagCompound.set(nbtTag.getKey(), NBTTagDouble.a(((DoubleNbtTag) nbtTag).getValue()));
            else if (nbtTag instanceof ByteArrayNbtTag)
                nbtTagCompound.set(nbtTag.getKey(), new NBTTagByteArray(((ByteArrayNbtTag) nbtTag).getValue()));
            else if (nbtTag instanceof StringNbtTag)
                nbtTagCompound.set(nbtTag.getKey(), NBTTagString.a(((StringNbtTag) nbtTag).getValue()));
            else if (nbtTag instanceof NbtList)
                nbtTagCompound.set(nbtTag.getKey(), createNBTTagList(((NbtList) nbtTag).getContent()));
            else if (nbtTag instanceof NbtCompound)
                nbtTagCompound.set(nbtTag.getKey(), createNBTTagCompound(((NbtCompound) nbtTag).getChildren()));
            else if (nbtTag instanceof IntArrayNbtTag)
                nbtTagCompound.set(nbtTag.getKey(), new NBTTagIntArray(((IntArrayNbtTag) nbtTag).getValue()));
            else if (nbtTag instanceof LongArrayNbtTag)
                nbtTagCompound.set(nbtTag.getKey(), new NBTTagLongArray(((LongArrayNbtTag) nbtTag).getValue()));
        }
        return nbtTagCompound;
    }

    private static NBTTagList createNBTTagList(List<NbtTag> nbtTags) {
        NBTTagList nbtTagList = new NBTTagList();
        for (NbtTag nbtTag : nbtTags) {
            if (nbtTag instanceof ByteNbtTag)
                nbtTagList.add(NBTTagByte.a(((ByteNbtTag) nbtTag).getValue()));
            else if (nbtTag instanceof ShortNbtTag)
                nbtTagList.add(NBTTagShort.a(((ShortNbtTag) nbtTag).getValue()));
            else if (nbtTag instanceof IntNbtTag)
                nbtTagList.add(NBTTagInt.a(((IntNbtTag) nbtTag).getValue()));
            else if (nbtTag instanceof LongNbtTag)
                nbtTagList.add(NBTTagLong.a(((LongNbtTag) nbtTag).getValue()));
            else if (nbtTag instanceof FloatNbtTag)
                nbtTagList.add(NBTTagFloat.a(((FloatNbtTag) nbtTag).getValue()));
            else if (nbtTag instanceof DoubleNbtTag)
                nbtTagList.add(NBTTagDouble.a(((DoubleNbtTag) nbtTag).getValue()));
            else if (nbtTag instanceof ByteArrayNbtTag)
                nbtTagList.add(new NBTTagByteArray(((ByteArrayNbtTag) nbtTag).getValue()));
            else if (nbtTag instanceof StringNbtTag)
                nbtTagList.add(NBTTagString.a(((StringNbtTag) nbtTag).getValue()));
            else if (nbtTag instanceof NbtList)
                nbtTagList.add(createNBTTagList(((NbtList) nbtTag).getContent()));
            else if (nbtTag instanceof NbtCompound)
                nbtTagList.add(createNBTTagCompound(((NbtCompound) nbtTag).getChildren()));
            else if (nbtTag instanceof IntArrayNbtTag)
                nbtTagList.add(new NBTTagIntArray(((IntArrayNbtTag) nbtTag).getValue()));
            else if (nbtTag instanceof LongArrayNbtTag)
                nbtTagList.add(new NBTTagLongArray(((LongArrayNbtTag) nbtTag).getValue()));
        }
        return nbtTagList;
    }

    private static @NotNull HashMap<String, NbtTag> getTagsFromCompound(@Nullable NBTTagCompound nbtTagCompound) {
        HashMap<String, NbtTag> nbtTagList = new HashMap<>();
        if (nbtTagCompound == null) return nbtTagList;
        for (String key : nbtTagCompound.getKeys()) {
            NBTBase nbtBase = nbtTagCompound.get(key);
            if (nbtBase == null) continue;
            if (nbtBase.getTypeId() == 1)
                nbtTagList.put(key, new ByteNbtTag(key, ((NBTNumber) nbtBase).asByte()));
            else if (nbtBase.getTypeId() == 2)
                nbtTagList.put(key, new ShortNbtTag(key, ((NBTNumber) nbtBase).asShort()));
            else if (nbtBase.getTypeId() == 3)
                nbtTagList.put(key, new IntNbtTag(key, ((NBTNumber) nbtBase).asInt()));
            else if (nbtBase.getTypeId() == 4)
                nbtTagList.put(key, new LongNbtTag(key, ((NBTNumber) nbtBase).asLong()));
            else if (nbtBase.getTypeId() == 5)
                nbtTagList.put(key, new FloatNbtTag(key, ((NBTNumber) nbtBase).asFloat()));
            else if (nbtBase.getTypeId() == 6)
                nbtTagList.put(key, new DoubleNbtTag(key, ((NBTNumber) nbtBase).asDouble()));
            else if (nbtBase.getTypeId() == 7)
                nbtTagList.put(key, new ByteArrayNbtTag(key, ((NBTTagByteArray) nbtBase).getBytes()));
            else if (nbtBase.getTypeId() == 8)
                nbtTagList.put(key, new StringNbtTag(key, nbtBase.asString()));
            else if (nbtBase.getTypeId() == 9)
                nbtTagList.put(key, new NbtList(key, getTagsFromList((NBTTagList) nbtBase)));
            else if (nbtBase.getTypeId() == 10)
                nbtTagList.put(key, new NbtCompound(key, getTagsFromCompound((NBTTagCompound) nbtBase)));
            else if (nbtBase.getTypeId() == 11)
                nbtTagList.put(key, new IntArrayNbtTag(key, ((NBTTagIntArray) nbtBase).getInts()));
            else if (nbtBase.getTypeId() == 12)
                nbtTagList.put(key, new LongArrayNbtTag(key, ((NBTTagLongArray) nbtBase).getLongs()));
        }
        return nbtTagList;
    }

    private static @NotNull List<NbtTag> getTagsFromList(@Nullable NBTTagList nbtTagList) {
        List<NbtTag> nbtTags = new ArrayList<>();
        if (nbtTagList == null) return nbtTags;
        for (NBTBase nbtBase : nbtTagList) {
            if (nbtBase == null) continue;
            if (nbtBase.getTypeId() == 1)
                nbtTags.add(new ByteNbtTag(null, ((NBTNumber) nbtBase).asByte()));
            else if (nbtBase.getTypeId() == 2)
                nbtTags.add(new ShortNbtTag(null, ((NBTNumber) nbtBase).asShort()));
            else if (nbtBase.getTypeId() == 3)
                nbtTags.add(new IntNbtTag(null, ((NBTNumber) nbtBase).asInt()));
            else if (nbtBase.getTypeId() == 4)
                nbtTags.add(new LongNbtTag(null, ((NBTNumber) nbtBase).asLong()));
            else if (nbtBase.getTypeId() == 5)
                nbtTags.add(new FloatNbtTag(null, ((NBTNumber) nbtBase).asFloat()));
            else if (nbtBase.getTypeId() == 6)
                nbtTags.add(new DoubleNbtTag(null, ((NBTNumber) nbtBase).asDouble()));
            else if (nbtBase.getTypeId() == 7)
                nbtTags.add(new ByteArrayNbtTag(null, ((NBTTagByteArray) nbtBase).getBytes()));
            else if (nbtBase.getTypeId() == 8)
                nbtTags.add(new StringNbtTag(null, nbtBase.asString()));
            else if (nbtBase.getTypeId() == 9)
                nbtTags.add(new NbtList(null, getTagsFromList((NBTTagList) nbtBase)));
            else if (nbtBase.getTypeId() == 10)
                nbtTags.add(new NbtCompound(null, getTagsFromCompound((NBTTagCompound) nbtBase)));
            else if (nbtBase.getTypeId() == 11)
                nbtTags.add(new IntArrayNbtTag(null, ((NBTTagIntArray) nbtBase).getInts()));
            else if (nbtBase.getTypeId() == 12)
                nbtTags.add(new LongArrayNbtTag(null, ((NBTTagLongArray) nbtBase).getLongs()));
        }
        return nbtTags;
    }

    /**
     * Removes the NBT tag associated with the given key
     *
     * @param itemStack The original ItemStack
     * @param key       The key of the NBT tag
     * @return The ItemStack with the NBT tag removed
     */
    public static @NotNull ItemStack removeTag(@NotNull ItemStack itemStack, @NotNull String key) {
        net.minecraft.server.v1_16_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound localNBTTagCompound = nmsItemStack.getOrCreateTag();
        localNBTTagCompound.remove(key);
        nmsItemStack.setTag(localNBTTagCompound);
        return CraftItemStack.asBukkitCopy(nmsItemStack);
    }

    /**
     * Adds an NBT tag to the given ItemStack
     *
     * @param itemStack The original ItemStack
     * @param key       The key of the NBT tag
     * @param value     The value of the NBT tag
     * @return The ItemStack with the NBT tag applied
     */
    public static @NotNull ItemStack setNBTString(@NotNull ItemStack itemStack, @NotNull String key, @NotNull String value) {
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
    public static @NotNull ItemStack setNBTInt(@NotNull ItemStack itemStack, @NotNull String key, @NotNull int value) {
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
    public static @NotNull ItemStack setNBTBoolean(@NotNull ItemStack itemStack, @NotNull String key, @NotNull boolean value) {
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
    public static @NotNull ItemStack setNBTIntCompound(@NotNull ItemStack itemStack, @NotNull String key, @NotNull HashMap<String, Integer> values) {
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
    public static @Nullable HashMap<String, Integer> getNBTIntCompound(@NotNull ItemStack itemStack, @NotNull String key) {
        net.minecraft.server.v1_16_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound nbtTagCompound = nmsItemStack.getTag();
        if (nbtTagCompound != null && nbtTagCompound.hasKey(key)) {
            HashMap<String, Integer> values = new HashMap<>();
            nbtTagCompound.getCompound(key).getKeys().forEach(s -> values.put(s, nbtTagCompound.getCompound(key).getInt(s)));
            return values;
        }
        return null;
    }

    /**
     * Copys the nbt compound associated with the key from one ItemStack to another
     *
     * @param from the ItemStack to copy from
     * @param to   the ItemStack to copy to
     * @param key  the specified key
     * @return the 'to' ItemStack with the new tag applied, if it existed
     */
    public static @NotNull ItemStack copyNBTTag(@NotNull ItemStack from, @NotNull ItemStack to, @NotNull String key) {
        net.minecraft.server.v1_16_R3.ItemStack nmsFrom = CraftItemStack.asNMSCopy(from);
        net.minecraft.server.v1_16_R3.ItemStack nmsTo = CraftItemStack.asNMSCopy(to);
        NBTTagCompound fromTagCompound = nmsFrom.getTag();
        NBTTagCompound toTagCompound = nmsTo.getOrCreateTag();
        if (fromTagCompound != null && fromTagCompound.hasKey(key)) {
            toTagCompound.set(key, fromTagCompound.get(key));
            nmsTo.setTag(toTagCompound);
            return CraftItemStack.asBukkitCopy(nmsTo);
        }
        return to;
    }
}