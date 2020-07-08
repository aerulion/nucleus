package net.aerulion.nucleus.api.base64;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
}