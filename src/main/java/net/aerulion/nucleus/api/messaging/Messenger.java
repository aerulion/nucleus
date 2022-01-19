package net.aerulion.nucleus.api.messaging;

import net.aerulion.nucleus.Main;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public interface Messenger {

  String getRawMessage();

  MessageLevel getMessageLevel();

  default void bossBar(final Audience recipient, final int seconds,
      final Placeholder... placeholders) {
    Main.messagingManager.bossBarMessage(getRawMessage(), getMessageLevel(), recipient, seconds,
        placeholders);
  }

  default void bossBar(final Audience recipient, final Placeholder... placeholders) {
    Main.messagingManager.bossBarMessage(getRawMessage(), getMessageLevel(), recipient, 5,
        placeholders);
  }

  default void bossBar(final Audience recipient, final NamespacedKey key, final int seconds,
      final Placeholder... placeholders) {
    Main.messagingManager.keyedBossBarMessage(getRawMessage(), getMessageLevel(), recipient,
        seconds, key, placeholders);
  }

  default void bossBar(final Audience recipient, final NamespacedKey key,
      final Placeholder... placeholders) {
    Main.messagingManager.keyedBossBarMessage(getRawMessage(), getMessageLevel(), recipient, 5, key,
        placeholders);
  }

  default void chat(final Audience recipient, final Placeholder... placeholders) {
    Main.messagingManager.chat(recipient, getRawMessage(), getMessageLevel(), placeholders);
  }

  default void console(final Placeholder... placeholders) {
    Main.messagingManager.chat(Bukkit.getConsoleSender(), getRawMessage(), getMessageLevel(),
        placeholders);
  }

  default @NotNull Component format(final Placeholder... placeholders) {
    return Main.messagingManager.format(getRawMessage(), getMessageLevel(), placeholders);
  }
}