package net.aerulion.nucleus.util.messaging;

import lombok.Getter;
import net.aerulion.nucleus.api.messaging.MessageLevel;
import net.aerulion.nucleus.api.messaging.Placeholder;
import net.kyori.adventure.audience.Audience;
import org.bukkit.NamespacedKey;

@Getter
class KeyedBossBarMessage extends BossBarMessage {

  private final NamespacedKey key;

  public KeyedBossBarMessage(final String message, final MessageLevel messageLevel,
      final Audience recipient, final int seconds, final NamespacedKey key,
      final Placeholder... placeholders) {
    super(message, messageLevel, recipient, seconds, placeholders);
    this.key = key;
  }
}