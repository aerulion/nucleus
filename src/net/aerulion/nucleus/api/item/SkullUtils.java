package net.aerulion.nucleus.api.item;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import javax.xml.bind.DatatypeConverter;
import java.lang.reflect.Field;
import java.util.UUID;

public class SkullUtils {
    private static Class<?> skullMetaClass;

    static {
        String version = org.bukkit.Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        try {
            skullMetaClass = Class.forName("org.bukkit.craftbukkit." + version + ".inventory.CraftMetaSkull");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ItemStack getSkull(String skinURL) {
        return getSkull(skinURL, 1);
    }

    public static ItemStack getSkull(String skinURL, int amount) {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD, amount);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        try {
            Field profileField = skullMetaClass.getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, getProfile(skinURL));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        skull.setItemMeta(meta);
        return skull;
    }

    private static GameProfile getProfile(String skinURL) {
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        String base64encoded = DatatypeConverter.printBase64Binary(("{textures:{SKIN:{url:\"" + skinURL + "\"}}}").getBytes());
        Property property = new Property("textures", base64encoded);
        profile.getProperties().put("textures", property);
        return profile;
    }
}
