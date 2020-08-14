package net.aerulion.nucleus.api.base64;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Base64Utils {
    public static String encodeItemStack(ItemStack itemStack) {
        String encodedItemStack = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream bukkitObjectOutputStream = new BukkitObjectOutputStream(byteArrayOutputStream);
            bukkitObjectOutputStream.writeObject(itemStack);
            bukkitObjectOutputStream.close();
            encodedItemStack = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        } catch (Exception ignored) {
        }
        return encodedItemStack;
    }

    public static ItemStack decodeItemStack(String encodedString) {
        ItemStack itemStack = null;
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.getDecoder().decode(encodedString));
            BukkitObjectInputStream bukkitObjectInputStream = new BukkitObjectInputStream(byteArrayInputStream);
            itemStack = (ItemStack) bukkitObjectInputStream.readObject();
            bukkitObjectInputStream.close();
        } catch (Exception ignored) {
        }
        return itemStack;
    }

    public static List<String> encodeItemStackList(List<ItemStack> itemStackList) {
        List<String> output = new ArrayList<>();
        for (ItemStack itemstack : itemStackList)
            output.add(encodeItemStack(itemstack));
        return output;
    }

    public static List<ItemStack> decodeItemStackList(List<String> stringList) {
        List<ItemStack> output = new ArrayList<>();
        for (String string : stringList)
            output.add(decodeItemStack(string));
        return output;
    }

    public static String encodeBufferedImage(BufferedImage bufferedImage, final String formatName) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, formatName, Base64.getEncoder().wrap(byteArrayOutputStream));
        return byteArrayOutputStream.toString(StandardCharsets.ISO_8859_1.name());
    }

    public static BufferedImage decodeBufferedImage(String image) throws IOException {
        BufferedImage bufferedImage;
        bufferedImage = ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(image)));
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        int[] pixels = bufferedImage.getRGB(0, 0, width, height, null, 0, width);
        BufferedImage copy = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        copy.setRGB(0, 0, width, height, pixels, 0, width);
        return copy;
    }
}