package net.aerulion.nucleus.api.nbt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.experimental.UtilityClass;
import net.aerulion.nucleus.api.nbt.tag.ByteArrayNbtTag;
import net.aerulion.nucleus.api.nbt.tag.ByteNbtTag;
import net.aerulion.nucleus.api.nbt.tag.DoubleNbtTag;
import net.aerulion.nucleus.api.nbt.tag.FloatNbtTag;
import net.aerulion.nucleus.api.nbt.tag.IntArrayNbtTag;
import net.aerulion.nucleus.api.nbt.tag.IntNbtTag;
import net.aerulion.nucleus.api.nbt.tag.LongArrayNbtTag;
import net.aerulion.nucleus.api.nbt.tag.LongNbtTag;
import net.aerulion.nucleus.api.nbt.tag.NbtCompound;
import net.aerulion.nucleus.api.nbt.tag.NbtList;
import net.aerulion.nucleus.api.nbt.tag.NbtTag;
import net.aerulion.nucleus.api.nbt.tag.ShortNbtTag;
import net.aerulion.nucleus.api.nbt.tag.StringNbtTag;
import net.minecraft.core.BlockPosition;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTNumber;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagByteArray;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagLongArray;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.level.World;
import net.minecraft.world.level.block.entity.TileEntity;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A utility class for NBT related stuff
 */
@UtilityClass
public final class NbtUtils {

  /**
   * Gets the current nbt tags of a block / tile entity
   *
   * @param block the block
   * @return Map of NbtTags
   */
  public static @NotNull Map<String, NbtTag> getTags(@NotNull Block block) {
    World world = ((CraftWorld) block.getWorld()).getHandle();
    NBTTagCompound nbtTagCompound = new NBTTagCompound();
    TileEntity tileEntity = world.getTileEntity(
        new BlockPosition(block.getX(), block.getY(), block.getZ()));
    if (tileEntity != null) {
      tileEntity.save(nbtTagCompound);
    }
    return getTagsFromCompound(nbtTagCompound);
  }

  /**
   * Gets the current nbt tags of an ItemStack
   *
   * @param itemStack the item
   * @return Map of NbtTags
   */
  public static @NotNull Map<String, NbtTag> getTags(@NotNull ItemStack itemStack) {
    return getTagsFromCompound(CraftItemStack.asNMSCopy(itemStack).getTag());
  }

  /**
   * Gets the nbt tag associated to the given key
   *
   * @param itemStack the item
   * @param key       the specified key
   * @return the associated NbtTag, or null if it doesn't exist
   */
  public static @Nullable NbtTag getTag(@NotNull ItemStack itemStack, String key) {
    @NotNull Map<String, NbtTag> tagHashMap = getTags(itemStack);
    return tagHashMap.get(key);
  }

  /**
   * Sets the nbt tags of an ItemStack
   *
   * @param itemStack the item
   * @param nbtTags   the map of nbt tags
   * @return the modified item
   */
  public static @NotNull ItemStack setTags(@NotNull ItemStack itemStack,
      @NotNull Map<String, NbtTag> nbtTags) {
    net.minecraft.world.item.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack.clone());
    nmsItemStack.setTag(createNBTTagCompound(nbtTags));
    return CraftItemStack.asBukkitCopy(nmsItemStack);
  }

  /**
   * Sets a given NbtTag to the ItemStack
   *
   * @param itemStack the target ItemStack
   * @param nbtTag    the NbtTag to set
   * @return the modified ItemStack
   */
  public static @NotNull ItemStack setTag(@NotNull ItemStack itemStack, @NotNull NbtTag nbtTag) {
    @NotNull HashMap<String, NbtTag> tagHashMap = new HashMap<>();
    tagHashMap.put(nbtTag.getKey(), nbtTag);
    @NotNull NBTTagCompound nbtTagCompound = createNBTTagCompound(tagHashMap);
    net.minecraft.world.item.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack.clone());
    nmsItemStack.getOrCreateTag().set(nbtTag.getKey(), nbtTagCompound.get(nbtTag.getKey()));
    return CraftItemStack.asBukkitCopy(nmsItemStack);
  }

  private static @NotNull NBTTagCompound createNBTTagCompound(
      @NotNull Map<String, NbtTag> nbtTags) {
    @NotNull NBTTagCompound nbtTagCompound = new NBTTagCompound();
    for (NbtTag nbtTag : nbtTags.values()) {
      if (nbtTag instanceof ByteNbtTag byteNbtTag) {
        nbtTagCompound.set(nbtTag.getKey(), NBTTagByte.a(byteNbtTag.getValue()));
      } else if (nbtTag instanceof ShortNbtTag shortNbtTag) {
        nbtTagCompound.set(nbtTag.getKey(), NBTTagShort.a(shortNbtTag.getValue()));
      } else if (nbtTag instanceof IntNbtTag intNbtTag) {
        nbtTagCompound.set(nbtTag.getKey(), NBTTagInt.a(intNbtTag.getValue()));
      } else if (nbtTag instanceof LongNbtTag longNbtTag) {
        nbtTagCompound.set(nbtTag.getKey(), NBTTagLong.a(longNbtTag.getValue()));
      } else if (nbtTag instanceof FloatNbtTag floatNbtTag) {
        nbtTagCompound.set(nbtTag.getKey(), NBTTagFloat.a(floatNbtTag.getValue()));
      } else if (nbtTag instanceof DoubleNbtTag doubleNbtTag) {
        nbtTagCompound.set(nbtTag.getKey(), NBTTagDouble.a(doubleNbtTag.getValue()));
      } else if (nbtTag instanceof ByteArrayNbtTag byteArrayNbtTag) {
        nbtTagCompound.set(nbtTag.getKey(), new NBTTagByteArray(byteArrayNbtTag.getValue()));
      } else if (nbtTag instanceof StringNbtTag stringNbtTag) {
        nbtTagCompound.set(nbtTag.getKey(), NBTTagString.a(stringNbtTag.getValue()));
      } else if (nbtTag instanceof NbtList nbtList) {
        nbtTagCompound.set(nbtTag.getKey(), createNBTTagList(nbtList.getContent()));
      } else if (nbtTag instanceof NbtCompound nbtCompound) {
        nbtTagCompound.set(nbtTag.getKey(), createNBTTagCompound(nbtCompound.getChildren()));
      } else if (nbtTag instanceof IntArrayNbtTag intArrayNbtTag) {
        nbtTagCompound.set(nbtTag.getKey(), new NBTTagIntArray(intArrayNbtTag.getValue()));
      } else if (nbtTag instanceof LongArrayNbtTag longArrayNbtTag) {
        nbtTagCompound.set(nbtTag.getKey(), new NBTTagLongArray(longArrayNbtTag.getValue()));
      }
    }
    return nbtTagCompound;
  }

  private static @NotNull NBTTagList createNBTTagList(@NotNull List<NbtTag> nbtTags) {
    @NotNull NBTTagList nbtTagList = new NBTTagList();
    for (NbtTag nbtTag : nbtTags) {
      if (nbtTag instanceof ByteNbtTag byteNbtTag) {
        nbtTagList.add(NBTTagByte.a(byteNbtTag.getValue()));
      } else if (nbtTag instanceof ShortNbtTag shortNbtTag) {
        nbtTagList.add(NBTTagShort.a(shortNbtTag.getValue()));
      } else if (nbtTag instanceof IntNbtTag intNbtTag) {
        nbtTagList.add(NBTTagInt.a(intNbtTag.getValue()));
      } else if (nbtTag instanceof LongNbtTag longNbtTag) {
        nbtTagList.add(NBTTagLong.a(longNbtTag.getValue()));
      } else if (nbtTag instanceof FloatNbtTag floatNbtTag) {
        nbtTagList.add(NBTTagFloat.a(floatNbtTag.getValue()));
      } else if (nbtTag instanceof DoubleNbtTag doubleNbtTag) {
        nbtTagList.add(NBTTagDouble.a(doubleNbtTag.getValue()));
      } else if (nbtTag instanceof ByteArrayNbtTag byteArrayNbtTag) {
        nbtTagList.add(new NBTTagByteArray(byteArrayNbtTag.getValue()));
      } else if (nbtTag instanceof StringNbtTag stringNbtTag) {
        nbtTagList.add(NBTTagString.a(stringNbtTag.getValue()));
      } else if (nbtTag instanceof NbtList nbtList) {
        nbtTagList.add(createNBTTagList(nbtList.getContent()));
      } else if (nbtTag instanceof NbtCompound nbtCompound) {
        nbtTagList.add(createNBTTagCompound(nbtCompound.getChildren()));
      } else if (nbtTag instanceof IntArrayNbtTag intArrayNbtTag) {
        nbtTagList.add(new NBTTagIntArray(intArrayNbtTag.getValue()));
      } else if (nbtTag instanceof LongArrayNbtTag longArrayNbtTag) {
        nbtTagList.add(new NBTTagLongArray(longArrayNbtTag.getValue()));
      }
    }
    return nbtTagList;
  }

  private static @NotNull Map<String, NbtTag> getTagsFromCompound(
      @Nullable NBTTagCompound nbtTagCompound) {
    @NotNull Map<String, NbtTag> nbtTagList = new HashMap<>();
    if (nbtTagCompound == null) {
      return nbtTagList;
    }
    for (String key : nbtTagCompound.getKeys()) {
      @Nullable NBTBase nbtBase = nbtTagCompound.get(key);
      if (nbtBase == null) {
        continue;
      }
      if (nbtBase.getTypeId() == 1) {
        nbtTagList.put(key, new ByteNbtTag(key, ((NBTNumber) nbtBase).asByte()));
      } else if (nbtBase.getTypeId() == 2) {
        nbtTagList.put(key, new ShortNbtTag(key, ((NBTNumber) nbtBase).asShort()));
      } else if (nbtBase.getTypeId() == 3) {
        nbtTagList.put(key, new IntNbtTag(key, ((NBTNumber) nbtBase).asInt()));
      } else if (nbtBase.getTypeId() == 4) {
        nbtTagList.put(key, new LongNbtTag(key, ((NBTNumber) nbtBase).asLong()));
      } else if (nbtBase.getTypeId() == 5) {
        nbtTagList.put(key, new FloatNbtTag(key, ((NBTNumber) nbtBase).asFloat()));
      } else if (nbtBase.getTypeId() == 6) {
        nbtTagList.put(key, new DoubleNbtTag(key, ((NBTNumber) nbtBase).asDouble()));
      } else if (nbtBase.getTypeId() == 7) {
        nbtTagList.put(key, new ByteArrayNbtTag(key, ((NBTTagByteArray) nbtBase).getBytes()));
      } else if (nbtBase.getTypeId() == 8) {
        nbtTagList.put(key, new StringNbtTag(key, nbtBase.asString()));
      } else if (nbtBase.getTypeId() == 9) {
        nbtTagList.put(key, new NbtList(key, getTagsFromList((NBTTagList) nbtBase)));
      } else if (nbtBase.getTypeId() == 10) {
        nbtTagList.put(key, new NbtCompound(key, getTagsFromCompound((NBTTagCompound) nbtBase)));
      } else if (nbtBase.getTypeId() == 11) {
        nbtTagList.put(key, new IntArrayNbtTag(key, ((NBTTagIntArray) nbtBase).getInts()));
      } else if (nbtBase.getTypeId() == 12) {
        nbtTagList.put(key, new LongArrayNbtTag(key, ((NBTTagLongArray) nbtBase).getLongs()));
      }
    }
    return nbtTagList;
  }

  private static @NotNull List<NbtTag> getTagsFromList(@Nullable NBTTagList nbtTagList) {
    @NotNull List<NbtTag> nbtTags = new ArrayList<>();
    if (nbtTagList == null) {
      return nbtTags;
    }
    for (@Nullable NBTBase nbtBase : nbtTagList) {
      if (nbtBase == null) {
        continue;
      }
      if (nbtBase.getTypeId() == 1) {
        nbtTags.add(new ByteNbtTag(null, ((NBTNumber) nbtBase).asByte()));
      } else if (nbtBase.getTypeId() == 2) {
        nbtTags.add(new ShortNbtTag(null, ((NBTNumber) nbtBase).asShort()));
      } else if (nbtBase.getTypeId() == 3) {
        nbtTags.add(new IntNbtTag(null, ((NBTNumber) nbtBase).asInt()));
      } else if (nbtBase.getTypeId() == 4) {
        nbtTags.add(new LongNbtTag(null, ((NBTNumber) nbtBase).asLong()));
      } else if (nbtBase.getTypeId() == 5) {
        nbtTags.add(new FloatNbtTag(null, ((NBTNumber) nbtBase).asFloat()));
      } else if (nbtBase.getTypeId() == 6) {
        nbtTags.add(new DoubleNbtTag(null, ((NBTNumber) nbtBase).asDouble()));
      } else if (nbtBase.getTypeId() == 7) {
        nbtTags.add(new ByteArrayNbtTag(null, ((NBTTagByteArray) nbtBase).getBytes()));
      } else if (nbtBase.getTypeId() == 8) {
        nbtTags.add(new StringNbtTag(null, nbtBase.asString()));
      } else if (nbtBase.getTypeId() == 9) {
        nbtTags.add(new NbtList(null, getTagsFromList((NBTTagList) nbtBase)));
      } else if (nbtBase.getTypeId() == 10) {
        nbtTags.add(new NbtCompound(null, getTagsFromCompound((NBTTagCompound) nbtBase)));
      } else if (nbtBase.getTypeId() == 11) {
        nbtTags.add(new IntArrayNbtTag(null, ((NBTTagIntArray) nbtBase).getInts()));
      } else if (nbtBase.getTypeId() == 12) {
        nbtTags.add(new LongArrayNbtTag(null, ((NBTTagLongArray) nbtBase).getLongs()));
      }
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
    net.minecraft.world.item.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
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
  public static @NotNull ItemStack setNBTString(@NotNull ItemStack itemStack, @NotNull String key,
      @NotNull String value) {
    net.minecraft.world.item.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
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
    net.minecraft.world.item.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
    @Nullable NBTTagCompound localNBTTagCompound = nmsItemStack.getTag();
    if ((localNBTTagCompound != null) && (localNBTTagCompound.hasKey(key))) {
      return localNBTTagCompound.getString(key);
    }
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
  public static @NotNull ItemStack setNBTInt(@NotNull ItemStack itemStack, @NotNull String key,
      @NotNull int value) {
    net.minecraft.world.item.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
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
    net.minecraft.world.item.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
    @Nullable NBTTagCompound localNBTTagCompound = nmsItemStack.getTag();
    if ((localNBTTagCompound != null) && (localNBTTagCompound.hasKey(key))) {
      return localNBTTagCompound.getInt(key);
    }
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
  public static @NotNull ItemStack setNBTBoolean(@NotNull ItemStack itemStack, @NotNull String key,
      @NotNull boolean value) {
    net.minecraft.world.item.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
    NBTTagCompound nbtTagCompound = nmsItemStack.getOrCreateTag();
    nbtTagCompound.setBoolean(key, value);
    nmsItemStack.setTag(nbtTagCompound);
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
    net.minecraft.world.item.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
    @Nullable NBTTagCompound nbtTagCompound = nmsItemStack.getTag();
    return ((nbtTagCompound != null) && (nbtTagCompound.hasKey(key))) && nbtTagCompound.getBoolean(
        key);
  }

  /**
   * Adds an NBT tag compound to the given ItemStack
   *
   * @param itemStack The original ItemStack
   * @param key       The key of the NBT compound
   * @param values    A Map representing key and value of the new compound
   * @return The ItemStack with the NBT compound applied
   */
  public static @NotNull ItemStack setNBTIntCompound(@NotNull ItemStack itemStack,
      @NotNull String key, @NotNull Map<String, Integer> values) {
    net.minecraft.world.item.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
    NBTTagCompound nbtTagCompound = nmsItemStack.getOrCreateTag();
    @NotNull NBTTagCompound nbtTagCompound2 = new NBTTagCompound();
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
  public static @Nullable Map<String, Integer> getNBTIntCompound(@NotNull ItemStack itemStack,
      @NotNull String key) {
    net.minecraft.world.item.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
    @Nullable NBTTagCompound nbtTagCompound = nmsItemStack.getTag();
    if (nbtTagCompound != null && nbtTagCompound.hasKey(key)) {
      @NotNull HashMap<String, Integer> values = new HashMap<>();
      nbtTagCompound.getCompound(key).getKeys()
          .forEach(s -> values.put(s, nbtTagCompound.getCompound(key).getInt(s)));
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
  public static @NotNull ItemStack copyNBTTag(@NotNull ItemStack from, @NotNull ItemStack to,
      @NotNull String key) {
    net.minecraft.world.item.ItemStack nmsFrom = CraftItemStack.asNMSCopy(from);
    net.minecraft.world.item.ItemStack nmsTo = CraftItemStack.asNMSCopy(to);
    @Nullable NBTTagCompound fromTagCompound = nmsFrom.getTag();
    NBTTagCompound toTagCompound = nmsTo.getOrCreateTag();
    if (fromTagCompound != null && fromTagCompound.hasKey(key)) {
      toTagCompound.set(key, fromTagCompound.get(key));
      nmsTo.setTag(toTagCompound);
      return CraftItemStack.asBukkitCopy(nmsTo);
    }
    return to;
  }
}