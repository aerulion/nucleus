package net.aerulion.nucleus.api.item;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import java.util.Base64;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Skull;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SkullUtils {

  public static @NotNull ItemStack getSkull(final @NotNull String skinURL) {
    return getSkull(skinURL, UUID.randomUUID(), 1);
  }

  public static @NotNull ItemStack getSkull(final @NotNull String skinURL, final int amount) {
    return getSkull(skinURL, UUID.randomUUID(), amount);
  }

  public static @NotNull ItemStack getSkull(final @NotNull String skinURL, final UUID uuid) {
    return getSkull(skinURL, uuid, 1);
  }

  public static @NotNull ItemStack getSkull(final @NotNull String skinURL, final UUID uuid,
      final int amount) {
    final @NotNull ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
    if (skinURL.isEmpty()) {
      return itemStack;
    }
    final @NotNull SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
    if (skullMeta == null) {
      return itemStack;
    }
    final @NotNull PlayerProfile playerProfile = Bukkit.createProfile(uuid, null);
    playerProfile.setProperty(new ProfileProperty("textures", Base64.getEncoder()
        .encodeToString(("{textures:{SKIN:{url:\"" + skinURL + "\"}}}").getBytes())));
    skullMeta.setPlayerProfile(playerProfile);
    itemStack.setItemMeta(skullMeta);
    itemStack.setAmount(amount);
    return itemStack;
  }

  public static @Nullable String getSkullURL(final @NotNull ItemStack itemStack,
      final boolean trimmed) {
    if (itemStack.getType() != Material.PLAYER_HEAD) {
      return null;
    }
    final @NotNull SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
    if (skullMeta == null) {
      return null;
    }
    final @Nullable PlayerProfile playerProfile = skullMeta.getPlayerProfile();
    if (playerProfile == null) {
      return null;
    }
    final ProfileProperty profileProperty = playerProfile.getProperties().stream()
        .filter(profileProperty1 -> profileProperty1.getName().equals("textures")).findFirst()
        .orElse(null);
    if (profileProperty != null) {
      final @NotNull String decodedString = new String(
          Base64.getDecoder().decode(profileProperty.getValue()));
      final String @NotNull [] split = decodedString.split("/");
      final String urlPart = split[split.length - 1];
      final @NotNull String urlString = urlPart.substring(0, urlPart.indexOf('"'));
      if (trimmed) {
        return urlString;
      } else {
        return "https://textures.minecraft.net/texture/" + urlString;
      }
    }
    return null;
  }

  public static @Nullable UUID getSkullUUID(final @NotNull ItemStack itemStack) {
    if (itemStack.getType() != Material.PLAYER_HEAD) {
      return null;
    }
    final @NotNull SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
    if (skullMeta == null) {
      return null;
    }
    final @Nullable PlayerProfile playerProfile = skullMeta.getPlayerProfile();
    if (playerProfile == null) {
      return null;
    }
    return playerProfile.getId();
  }

  public static @Nullable UUID getSkullUUID(final @NotNull Skull skull) {
    final @Nullable PlayerProfile playerProfile = skull.getPlayerProfile();
    if (playerProfile == null) {
      return null;
    }
    return playerProfile.getId();
  }
}