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
import net.minecraft.nbt.ByteArrayTag;
import net.minecraft.nbt.ByteTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.IntArrayTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.LongArrayTag;
import net.minecraft.nbt.LongTag;
import net.minecraft.nbt.NumericTag;
import net.minecraft.nbt.ShortTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import org.bukkit.craftbukkit.v1_19_R2.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_19_R2.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A utility class for NBT related stuff
 */
@UtilityClass
public final class NbtUtils {

  /**
   * Gets the current nbt tags of an entity
   *
   * @param entity the entity
   * @return Map of NbtTags
   */
  public static @NotNull Map<String, NbtTag> getTags(final @NotNull Entity entity) {
    final net.minecraft.world.entity.Entity nmsEntity = ((CraftEntity) entity).getHandle();
    final @NotNull CompoundTag nbtTagCompound = new CompoundTag();
    nmsEntity.save(nbtTagCompound);
    return getTagsFromCompound(nbtTagCompound);
  }

  /**
   * Gets the current nbt tags of an ItemStack
   *
   * @param itemStack the item
   * @return Map of NbtTags
   */
  public static @NotNull Map<String, NbtTag> getTags(final @NotNull ItemStack itemStack) {
    return getTagsFromCompound(CraftItemStack.asNMSCopy(itemStack).getTag());
  }

  /**
   * Gets the nbt tag associated to the given key
   *
   * @param itemStack the item
   * @param key       the specified key
   * @return the associated NbtTag, or null if it doesn't exist
   */
  public static @Nullable NbtTag getTag(final @NotNull ItemStack itemStack,
      final @NotNull String key) {
    final @NotNull Map<String, NbtTag> tagHashMap = getTags(itemStack);
    return tagHashMap.get(key);
  }

  /**
   * Sets the nbt tags of an ItemStack
   *
   * @param itemStack the item
   * @param nbtTags   the map of nbt tags
   * @return the modified item
   */
  public static @NotNull ItemStack setTags(final @NotNull ItemStack itemStack,
      final @NotNull Map<String, NbtTag> nbtTags) {
    final @NotNull net.minecraft.world.item.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(
        itemStack.clone());
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
  public static @NotNull ItemStack setTag(final @NotNull ItemStack itemStack,
      final @NotNull NbtTag nbtTag) {
    final @NotNull HashMap<String, NbtTag> tagHashMap = new HashMap<>();
    tagHashMap.put(nbtTag.getKey(), nbtTag);
    final @NotNull CompoundTag compoundTag = createNBTTagCompound(tagHashMap);
    final @NotNull net.minecraft.world.item.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(
        itemStack.clone());
    nmsItemStack.getOrCreateTag().put(nbtTag.getKey(), compoundTag.get(nbtTag.getKey()));
    return CraftItemStack.asBukkitCopy(nmsItemStack);
  }

  private static @NotNull CompoundTag createNBTTagCompound(
      final @NotNull Map<String, NbtTag> nbtTags) {
    final @NotNull CompoundTag compoundTag = new CompoundTag();
    for (final NbtTag nbtTag : nbtTags.values()) {
      if (nbtTag instanceof ByteNbtTag byteNbtTag) {
        compoundTag.put(nbtTag.getKey(), ByteTag.valueOf(byteNbtTag.getValue()));
      } else if (nbtTag instanceof ShortNbtTag shortNbtTag) {
        compoundTag.put(nbtTag.getKey(), ShortTag.valueOf(shortNbtTag.getValue()));
      } else if (nbtTag instanceof IntNbtTag intNbtTag) {
        compoundTag.put(nbtTag.getKey(), IntTag.valueOf(intNbtTag.getValue()));
      } else if (nbtTag instanceof LongNbtTag longNbtTag) {
        compoundTag.put(nbtTag.getKey(), LongTag.valueOf(longNbtTag.getValue()));
      } else if (nbtTag instanceof FloatNbtTag floatNbtTag) {
        compoundTag.put(nbtTag.getKey(), FloatTag.valueOf(floatNbtTag.getValue()));
      } else if (nbtTag instanceof DoubleNbtTag doubleNbtTag) {
        compoundTag.put(nbtTag.getKey(), DoubleTag.valueOf(doubleNbtTag.getValue()));
      } else if (nbtTag instanceof ByteArrayNbtTag byteArrayNbtTag) {
        compoundTag.put(nbtTag.getKey(), new ByteArrayTag(byteArrayNbtTag.getValue()));
      } else if (nbtTag instanceof StringNbtTag stringNbtTag) {
        compoundTag.put(nbtTag.getKey(), StringTag.valueOf(stringNbtTag.getValue()));
      } else if (nbtTag instanceof NbtList nbtList) {
        compoundTag.put(nbtTag.getKey(), createNBTTagList(nbtList.getContent()));
      } else if (nbtTag instanceof NbtCompound nbtCompound) {
        compoundTag.put(nbtTag.getKey(), createNBTTagCompound(nbtCompound.getChildren()));
      } else if (nbtTag instanceof IntArrayNbtTag intArrayNbtTag) {
        compoundTag.put(nbtTag.getKey(), new IntArrayTag(intArrayNbtTag.getValue()));
      } else if (nbtTag instanceof LongArrayNbtTag longArrayNbtTag) {
        compoundTag.put(nbtTag.getKey(), new LongArrayTag(longArrayNbtTag.getValue()));
      }
    }
    return compoundTag;
  }

  private static @NotNull ListTag createNBTTagList(final @NotNull List<NbtTag> nbtTags) {
    final @NotNull ListTag nbtTagList = new ListTag();
    for (final NbtTag nbtTag : nbtTags) {
      if (nbtTag instanceof ByteNbtTag byteNbtTag) {
        nbtTagList.add(ByteTag.valueOf(byteNbtTag.getValue()));
      } else if (nbtTag instanceof ShortNbtTag shortNbtTag) {
        nbtTagList.add(ShortTag.valueOf(shortNbtTag.getValue()));
      } else if (nbtTag instanceof IntNbtTag intNbtTag) {
        nbtTagList.add(IntTag.valueOf(intNbtTag.getValue()));
      } else if (nbtTag instanceof LongNbtTag longNbtTag) {
        nbtTagList.add(LongTag.valueOf(longNbtTag.getValue()));
      } else if (nbtTag instanceof FloatNbtTag floatNbtTag) {
        nbtTagList.add(FloatTag.valueOf(floatNbtTag.getValue()));
      } else if (nbtTag instanceof DoubleNbtTag doubleNbtTag) {
        nbtTagList.add(DoubleTag.valueOf(doubleNbtTag.getValue()));
      } else if (nbtTag instanceof ByteArrayNbtTag byteArrayNbtTag) {
        nbtTagList.add(new ByteArrayTag(byteArrayNbtTag.getValue()));
      } else if (nbtTag instanceof StringNbtTag stringNbtTag) {
        nbtTagList.add(StringTag.valueOf(stringNbtTag.getValue()));
      } else if (nbtTag instanceof NbtList nbtList) {
        nbtTagList.add(createNBTTagList(nbtList.getContent()));
      } else if (nbtTag instanceof NbtCompound nbtCompound) {
        nbtTagList.add(createNBTTagCompound(nbtCompound.getChildren()));
      } else if (nbtTag instanceof IntArrayNbtTag intArrayNbtTag) {
        nbtTagList.add(new IntArrayTag(intArrayNbtTag.getValue()));
      } else if (nbtTag instanceof LongArrayNbtTag longArrayNbtTag) {
        nbtTagList.add(new LongArrayTag(longArrayNbtTag.getValue()));
      }
    }
    return nbtTagList;
  }

  private static @NotNull Map<String, NbtTag> getTagsFromCompound(
      final @Nullable CompoundTag nbtTagCompound) {
    final @NotNull Map<String, NbtTag> nbtTagList = new HashMap<>();
    if (nbtTagCompound == null) {
      return nbtTagList;
    }
    for (final @NotNull String key : nbtTagCompound.getAllKeys()) {
      final @Nullable Tag tag = nbtTagCompound.get(key);
      if (tag == null) {
        continue;
      }
      switch (tag.getId()) {
        case 1 -> nbtTagList.put(key, new ByteNbtTag(key, ((NumericTag) tag).getAsByte()));
        case 2 -> nbtTagList.put(key, new ShortNbtTag(key, ((NumericTag) tag).getAsShort()));
        case 3 -> nbtTagList.put(key, new IntNbtTag(key, ((NumericTag) tag).getAsInt()));
        case 4 -> nbtTagList.put(key, new LongNbtTag(key, ((NumericTag) tag).getAsLong()));
        case 5 -> nbtTagList.put(key, new FloatNbtTag(key, ((NumericTag) tag).getAsFloat()));
        case 6 -> nbtTagList.put(key, new DoubleNbtTag(key, ((NumericTag) tag).getAsDouble()));
        case 7 -> nbtTagList.put(key,
            new ByteArrayNbtTag(key, ((ByteArrayTag) tag).getAsByteArray()));
        case 8 -> nbtTagList.put(key, new StringNbtTag(key, tag.getAsString()));
        case 9 -> nbtTagList.put(key, new NbtList(key, getTagsFromList((ListTag) tag)));
        case 10 -> nbtTagList.put(key,
            new NbtCompound(key, getTagsFromCompound((CompoundTag) tag)));
        case 11 -> nbtTagList.put(key,
            new IntArrayNbtTag(key, ((IntArrayTag) tag).getAsIntArray()));
        case 12 -> nbtTagList.put(key,
            new LongArrayNbtTag(key, ((LongArrayTag) tag).getAsLongArray()));
        default -> throw new IllegalStateException("Unexpected value: " + tag.getId());
      }
    }
    return nbtTagList;
  }

  private static @NotNull List<NbtTag> getTagsFromList(final @Nullable ListTag nbtTagList) {
    final @NotNull List<NbtTag> nbtTags = new ArrayList<>();
    if (nbtTagList == null) {
      return nbtTags;
    }
    for (final @Nullable Tag tag : nbtTagList) {
      if (tag == null) {
        continue;
      }
      switch (tag.getId()) {
        case 1 -> nbtTags.add(new ByteNbtTag(null, ((NumericTag) tag).getAsByte()));
        case 2 -> nbtTags.add(new ShortNbtTag(null, ((NumericTag) tag).getAsShort()));
        case 3 -> nbtTags.add(new IntNbtTag(null, ((NumericTag) tag).getAsInt()));
        case 4 -> nbtTags.add(new LongNbtTag(null, ((NumericTag) tag).getAsLong()));
        case 5 -> nbtTags.add(new FloatNbtTag(null, ((NumericTag) tag).getAsFloat()));
        case 6 -> nbtTags.add(new DoubleNbtTag(null, ((NumericTag) tag).getAsDouble()));
        case 7 -> nbtTags.add(new ByteArrayNbtTag(null, ((ByteArrayTag) tag).getAsByteArray()));
        case 8 -> nbtTags.add(new StringNbtTag(null, tag.getAsString()));
        case 9 -> nbtTags.add(new NbtList(null, getTagsFromList((ListTag) tag)));
        case 10 -> nbtTags.add(new NbtCompound(null, getTagsFromCompound((CompoundTag) tag)));
        case 11 -> nbtTags.add(new IntArrayNbtTag(null, ((IntArrayTag) tag).getAsIntArray()));
        case 12 -> nbtTags.add(new LongArrayNbtTag(null, ((LongArrayTag) tag).getAsLongArray()));
        default -> throw new IllegalStateException("Unexpected value: " + tag.getId());
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
  public static @NotNull ItemStack removeTag(final @NotNull ItemStack itemStack,
      final @NotNull String key) {
    final @NotNull net.minecraft.world.item.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(
        itemStack);
    final @NotNull CompoundTag compoundTag = nmsItemStack.getOrCreateTag();
    compoundTag.remove(key);
    nmsItemStack.setTag(compoundTag);
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
  public static @NotNull ItemStack setNBTString(final @NotNull ItemStack itemStack,
      final @NotNull String key, final @NotNull String value) {
    final @NotNull net.minecraft.world.item.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(
        itemStack);
    final @NotNull CompoundTag compoundTag = nmsItemStack.getOrCreateTag();
    compoundTag.putString(key, value);
    nmsItemStack.setTag(compoundTag);
    return CraftItemStack.asBukkitCopy(nmsItemStack);
  }

  /**
   * Gets the value of an NBT tag using a given key
   *
   * @param itemStack The given ItemStack
   * @param key       The specified key
   * @return The value associated with the given key or an empty String, if the key doesn't exist
   */
  public static @NotNull String getNBTString(final @NotNull ItemStack itemStack,
      final @NotNull String key) {
    final @NotNull net.minecraft.world.item.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(
        itemStack);
    final @Nullable CompoundTag compoundTag = nmsItemStack.getTag();
    if ((compoundTag != null) && (compoundTag.contains(key))) {
      return compoundTag.getString(key);
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
  public static @NotNull ItemStack setNBTInt(final @NotNull ItemStack itemStack,
      final @NotNull String key, final int value) {
    final @NotNull net.minecraft.world.item.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(
        itemStack);
    final @NotNull CompoundTag compoundTag = nmsItemStack.getOrCreateTag();
    compoundTag.putInt(key, value);
    nmsItemStack.setTag(compoundTag);
    return CraftItemStack.asBukkitCopy(nmsItemStack);
  }

  /**
   * Gets the value of an NBT tag using a given key
   *
   * @param itemStack The given ItemStack
   * @param key       The specified key
   * @return The value associated with the given key or 0, if the key doesn't exist
   */
  public static int getNBTInt(final @NotNull ItemStack itemStack, final @NotNull String key) {
    final @NotNull net.minecraft.world.item.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(
        itemStack);
    final @Nullable CompoundTag compoundTag = nmsItemStack.getTag();
    if ((compoundTag != null) && (compoundTag.contains(key))) {
      return compoundTag.getInt(key);
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
  public static @NotNull ItemStack setNBTBoolean(final @NotNull ItemStack itemStack,
      final @NotNull String key, final boolean value) {
    final @NotNull net.minecraft.world.item.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(
        itemStack);
    final @NotNull CompoundTag compoundTag = nmsItemStack.getOrCreateTag();
    compoundTag.putBoolean(key, value);
    nmsItemStack.setTag(compoundTag);
    return CraftItemStack.asBukkitCopy(nmsItemStack);
  }

  /**
   * Gets the value of an NBT tag using a given key
   *
   * @param itemStack The given ItemStack
   * @param key       The specified key
   * @return The value associated with the given key or 'false', if the key doesn't exist
   */
  public static boolean getNBTBoolean(final @NotNull ItemStack itemStack,
      final @NotNull String key) {
    final @NotNull net.minecraft.world.item.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(
        itemStack);
    final @Nullable CompoundTag compoundTag = nmsItemStack.getTag();
    return ((compoundTag != null) && (compoundTag.contains(key))) && compoundTag.getBoolean(key);
  }

  /**
   * Adds an NBT tag compound to the given ItemStack
   *
   * @param itemStack The original ItemStack
   * @param key       The key of the NBT compound
   * @param values    A Map representing key and value of the new compound
   * @return The ItemStack with the NBT compound applied
   */
  public static @NotNull ItemStack setNBTIntCompound(final @NotNull ItemStack itemStack,
      final @NotNull String key, final @NotNull Map<String, Integer> values) {
    final @NotNull net.minecraft.world.item.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(
        itemStack);
    final @NotNull CompoundTag compoundTag = nmsItemStack.getOrCreateTag();
    final @NotNull CompoundTag compoundTag1 = new CompoundTag();
    values.forEach(compoundTag1::putInt);
    compoundTag.put(key, compoundTag1);
    nmsItemStack.setTag(compoundTag);
    return CraftItemStack.asBukkitCopy(nmsItemStack);
  }

  /**
   * Gets the value of an NBT compound using a given key
   *
   * @param itemStack The given ItemStack
   * @param key       The specified key
   * @return A Map containing the key value pairs or null, if the compound key doesn't exist
   */
  public static @Nullable Map<String, Integer> getNBTIntCompound(final @NotNull ItemStack itemStack,
      final @NotNull String key) {
    final @NotNull net.minecraft.world.item.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(
        itemStack);
    final @Nullable CompoundTag compoundTag = nmsItemStack.getTag();
    if (compoundTag != null && compoundTag.contains(key)) {
      final @NotNull HashMap<String, Integer> values = new HashMap<>();
      compoundTag.getCompound(key).getAllKeys()
          .forEach(s -> values.put(s, compoundTag.getCompound(key).getInt(s)));
      return values;
    }
    return null;
  }

  /**
   * Copys the nbt compound associated with the key from one ItemStack to another
   *
   * @param source the ItemStack to copy from
   * @param target the ItemStack to copy to
   * @param key    the specified key
   * @return the 'to' ItemStack with the new tag applied, if it existed
   */
  public static @NotNull ItemStack copyNBTTag(final @NotNull ItemStack source,
      final @NotNull ItemStack target, final @NotNull String key) {
    final @NotNull net.minecraft.world.item.ItemStack nmsFrom = CraftItemStack.asNMSCopy(source);
    final @NotNull net.minecraft.world.item.ItemStack nmsTo = CraftItemStack.asNMSCopy(target);
    final @Nullable CompoundTag sourceCompoundTag = nmsFrom.getTag();
    final @NotNull CompoundTag targetCompoundTag = nmsTo.getOrCreateTag();
    if (sourceCompoundTag != null && sourceCompoundTag.contains(key)) {
      targetCompoundTag.put(key, sourceCompoundTag.get(key));
      nmsTo.setTag(targetCompoundTag);
      return CraftItemStack.asBukkitCopy(nmsTo);
    }
    return target;
  }
}