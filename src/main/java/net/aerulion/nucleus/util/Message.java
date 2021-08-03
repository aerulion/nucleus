package net.aerulion.nucleus.util;

import net.aerulion.nucleus.api.messaging.MessageLevel;
import net.aerulion.nucleus.api.messaging.Messenger;
import net.aerulion.nucleus.api.messaging.Placeholder;
import net.kyori.adventure.audience.Audience;
import org.bukkit.Bukkit;

public enum Message {
  CONNECTION_POOL_INITIALIZED("Der ConnectionPool wurde initialisiert. Dauerte %timestamp%ms",
      MessageLevel.SUCCESS),
  CONNECTION_POOL_INITIALIZING("Initialisiere ConnectionPool...", MessageLevel.INFO),
  DISABLING_PLUGIN("Deaktiviere Plugin...", MessageLevel.INFO),
  ENABLING_PLUGIN("Aktiviere Plugin...", MessageLevel.INFO),
  ERROR_DEFAULT_MYSQL_CONFIG("Die Default MySQL Config Datei konnte nicht erstellt werden.",
      MessageLevel.ERROR),
  PLUGIN_DISABLED("Das Plugin wurde deaktiviert.", MessageLevel.SUCCESS),
  PLUGIN_ENABLED("Das Plugin wurde aktiviert.", MessageLevel.SUCCESS);

  private final String message;
  private final MessageLevel messageLevel;

  Message(String message, MessageLevel messageLevel) {
    this.message = message;
    this.messageLevel = messageLevel;
  }

  public void chat(Audience recipient, Placeholder... placeholders) {
    Messenger.chat(recipient, message, messageLevel, placeholders);
  }

  public void console(Placeholder... placeholders) {
    chat(Bukkit.getConsoleSender(), placeholders);
  }
}