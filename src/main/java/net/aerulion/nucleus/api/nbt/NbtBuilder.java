package net.aerulion.nucleus.api.nbt;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * Set NBT values in a builder-like pattern
 *
 * @since 1.15
 */
public class NbtBuilder {

    private ItemStack itemStack;

    private NbtBuilder(@NotNull ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Create a new NbtBuilder using an existing ItemStack
     *
     * @param itemStack The ItemStack used in the builder
     * @return The NbtBuilderInstance
     */
    public static NbtBuilder of(@NotNull ItemStack itemStack) {
        return new NbtBuilder(itemStack);
    }

    /**
     * Create a new NbtBuilder using a newly created ItemStack
     *
     * @param material The material used in the builder
     * @return The NbtBuilderInstance
     */
    public static NbtBuilder of(@NotNull Material material) {
        return of(new ItemStack(material));
    }

    /**
     * Create a new NbtBuilder using a newly created ItemStack
     *
     * @param material The material used in the builder
     * @param amount   The amount of the newly created ItemStack
     * @return The NbtBuilderInstance
     */
    public static NbtBuilder of(@NotNull Material material, @NotNull int amount) {
        return new NbtBuilder(new ItemStack(material, amount));
    }

    /**
     * Get the final ItemStack
     *
     * @return The ItemStack with all NBT data applied
     */
    public ItemStack build() {
        return itemStack;
    }

    /**
     * Adds an NBT tag to the given ItemStack
     *
     * @param key   The key of the NBT tag
     * @param value The value of the NBT tag
     * @return The NbtBuilder instance
     */
    public NbtBuilder withNBTString(@NotNull String key, @NotNull String value) {
        itemStack = NbtUtils.setNBTString(itemStack, key, value);
        return this;
    }

    /**
     * Adds an NBT tag to the given ItemStack
     *
     * @param key   The key of the NBT tag
     * @param value The value of the NBT tag
     * @return The NbtBuilder instance
     */
    public NbtBuilder withNBTInt(@NotNull String key, @NotNull int value) {
        itemStack = NbtUtils.setNBTInt(itemStack, key, value);
        return this;
    }

    /**
     * Adds an NBT tag to the given ItemStack
     *
     * @param key   The key of the NBT tag
     * @param value The value of the NBT tag
     * @return The NbtBuilder instance
     */
    public NbtBuilder withNBTBoolean(@NotNull String key, @NotNull boolean value) {
        itemStack = NbtUtils.setNBTBoolean(itemStack, key, value);
        return this;
    }

    /**
     * Adds an NBT tag to the given ItemStack
     *
     * @param key    The key of the NBT tag
     * @param values The values of the NBT tag compound
     * @return The NbtBuilder instance
     */
    public NbtBuilder withNBTIntCompound(@NotNull String key, @NotNull HashMap<String, Integer> values) {
        itemStack = NbtUtils.setNBTIntCompound(itemStack, key, values);
        return this;
    }
}