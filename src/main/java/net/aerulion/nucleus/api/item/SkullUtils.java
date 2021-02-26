package net.aerulion.nucleus.api.item;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import javax.xml.bind.DatatypeConverter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Base64;
import java.util.UUID;

public class SkullUtils {

    public static ItemStack getSkull(String skinURL) {
        return getSkull(skinURL, UUID.randomUUID(), 1);
    }

    public static ItemStack getSkull(String skinURL, int amount) {
        return getSkull(skinURL, UUID.randomUUID(), amount);
    }

    public static ItemStack getSkull(String skinURL, UUID uuid) {
        return getSkull(skinURL, uuid, 1);
    }

    public static ItemStack getSkull(String skinURL, UUID uuid, int amount) {
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
        if (skinURL.isEmpty()) return itemStack;
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        if (skullMeta == null) return itemStack;
        GameProfile profile = new GameProfile(uuid, null);
        profile.getProperties().put("textures", new Property("textures", Base64.getEncoder().encodeToString(("{textures:{SKIN:{url:\"" + skinURL + "\"}}}").getBytes())));
        try {
            Method method = skullMeta.getClass().getDeclaredMethod("setProfile", GameProfile.class);
            method.setAccessible(true);
            method.invoke(skullMeta, profile);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            ex.printStackTrace();
        }
        itemStack.setItemMeta(skullMeta);
        itemStack.setAmount(amount);
        return itemStack;
    }
}