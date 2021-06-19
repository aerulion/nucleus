package net.aerulion.nucleus.api.base64;

import lombok.experimental.UtilityClass;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * A utility class for Base64 encoding and decoding
 */
@UtilityClass
public final class Base64Utils {

    /**
     * Encodes an given ItemStack to an Base64 string
     *
     * @param itemStack The ItemStack to be encoded
     * @return The encoded string
     */
    public static @Nullable String encodeItemStack(ItemStack itemStack) {
        @Nullable String encodedItemStack = null;
        try {
            @NotNull ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            @NotNull BukkitObjectOutputStream bukkitObjectOutputStream = new BukkitObjectOutputStream(byteArrayOutputStream);
            bukkitObjectOutputStream.writeObject(itemStack);
            bukkitObjectOutputStream.close();
            encodedItemStack = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        } catch (Exception ignored) {
        }
        return encodedItemStack;
    }

    /**
     * Decodes an given string to an ItemStack
     *
     * @param encodedString The string to be decoded
     * @return The decoded ItemStack
     */
    public static @Nullable ItemStack decodeItemStack(String encodedString) {
        @Nullable ItemStack itemStack = null;
        try {
            @NotNull ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.getDecoder().decode(encodedString));
            @NotNull BukkitObjectInputStream bukkitObjectInputStream = new BukkitObjectInputStream(byteArrayInputStream);
            itemStack = (ItemStack) bukkitObjectInputStream.readObject();
            bukkitObjectInputStream.close();
        } catch (Exception ignored) {
        }
        return itemStack;
    }

    /**
     * Encodes an list of ItemStacks to an list of Base64 strings
     *
     * @param itemStackList The list to be encoded
     * @return The encoded list
     */
    public static @NotNull List<String> encodeItemStackList(@NotNull List<ItemStack> itemStackList) {
        @NotNull List<String> output = new ArrayList<>();
        for (ItemStack itemstack : itemStackList)
            output.add(encodeItemStack(itemstack));
        return output;
    }

    /**
     * Decodes an list of Base64 strings to an list of ItemStacks
     *
     * @param stringList The list to be decoded
     * @return The decoded list
     */
    public static @NotNull List<ItemStack> decodeItemStackList(@NotNull List<String> stringList) {
        @NotNull List<ItemStack> output = new ArrayList<>();
        for (String string : stringList)
            output.add(decodeItemStack(string));
        return output;
    }

    /**
     * Encodes an given BufferedImage to an Base64 string
     *
     * @param bufferedImage The BufferedImage to be encoded
     * @param formatName    a String containing the informal name of the format
     * @return The encoded image as an Base64 string
     * @throws IOException if an error occurs during writing or when not able to create required ImageOutputStream
     */
    public static String encodeBufferedImage(@NotNull BufferedImage bufferedImage, final @NotNull String formatName) throws IOException {
        @NotNull ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, formatName, Base64.getEncoder().wrap(byteArrayOutputStream));
        return byteArrayOutputStream.toString(StandardCharsets.ISO_8859_1.name());
    }

    /**
     * Decodes an given Base64 string to an BufferedImage
     *
     * @param image The encoded Base64 string
     * @return The decoded BufferedImage
     * @throws IOException if an error occurs during writing or when not able to create required ImageOutputStream
     */
    public static BufferedImage decodeBufferedImage(String image) throws IOException {
        BufferedImage bufferedImage;
        bufferedImage = ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(image)));
        return bufferedImage;
    }
}