package net.aerulion.nucleus.api.item;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import java.util.Base64;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SkullUtils {

  public static @NotNull ItemStack getSkull(@NotNull String skinURL) {
    return getSkull(skinURL, UUID.randomUUID(), 1);
  }

  public static @NotNull ItemStack getSkull(@NotNull String skinURL, int amount) {
    return getSkull(skinURL, UUID.randomUUID(), amount);
  }

  public static @NotNull ItemStack getSkull(@NotNull String skinURL, UUID uuid) {
    return getSkull(skinURL, uuid, 1);
  }

  public static @NotNull ItemStack getSkull(@NotNull String skinURL, UUID uuid, int amount) {
    @NotNull ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
    if (skinURL.isEmpty()) {
      return itemStack;
    }
    @NotNull SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
    if (skullMeta == null) {
      return itemStack;
    }
    PlayerProfile playerProfile = Bukkit.createProfile(uuid, null);
    playerProfile.setProperty(new ProfileProperty("textures", Base64.getEncoder()
        .encodeToString(("{textures:{SKIN:{url:\"" + skinURL + "\"}}}").getBytes())));
    skullMeta.setPlayerProfile(playerProfile);
    itemStack.setItemMeta(skullMeta);
    itemStack.setAmount(amount);
    return itemStack;
  }

  public static @Nullable String getSkullURL(@NotNull ItemStack itemStack, boolean trimmed) {
    if (!itemStack.getType().equals(Material.PLAYER_HEAD)) {
      return null;
    }
    @NotNull SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
    if (skullMeta == null) {
      return null;
    }
    PlayerProfile playerProfile = skullMeta.getPlayerProfile();
    if (playerProfile == null) {
      return null;
    }
    ProfileProperty profileProperty = playerProfile.getProperties().stream()
        .filter(profileProperty1 -> profileProperty1.getName().equals("textures")).findFirst()
        .orElse(null);
    if (profileProperty != null) {
      String decodedString = new String(Base64.getDecoder().decode(profileProperty.getValue()));
      String[] split = decodedString.split("/");
      String urlPart = split[split.length - 1];
      String urlString = urlPart.substring(0, urlPart.indexOf('"'));
      if (trimmed) {
        return urlString;
      } else {
        return "https://textures.minecraft.net/texture/" + urlString;
      }
    }
    return null;
  }

  public static @Nullable UUID getSkullUUID(@NotNull ItemStack itemStack) {
    if (!itemStack.getType().equals(Material.PLAYER_HEAD)) {
      return null;
    }
    @NotNull SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
    if (skullMeta == null) {
      return null;
    }
    PlayerProfile playerProfile = skullMeta.getPlayerProfile();
    if (playerProfile == null) {
      return null;
    }
    return playerProfile.getId();
  }
}