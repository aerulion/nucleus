package net.aerulion.nucleus.util;

import lombok.Getter;
import net.aerulion.nucleus.api.messaging.MessageLevel;
import net.aerulion.nucleus.api.messaging.Messenger;

@Getter
public enum Message implements Messenger {
  CONNECTION_POOL_INITIALIZED("Der ConnectionPool wurde initialisiert. Dauerte %timestamp%ms",
      MessageLevel.SUCCESS),
  CONNECTION_POOL_INITIALIZING("Initialisiere ConnectionPool...", MessageLevel.INFO),
  DISABLING_PLUGIN("Deaktiviere Plugin...", MessageLevel.INFO),
  ENABLING_PLUGIN("Aktiviere Plugin...", MessageLevel.INFO),
  ERROR_DEFAULT_MYSQL_CONFIG("Die Default MySQL Config Datei konnte nicht erstellt werden.",
      MessageLevel.ERROR),
  PLUGIN_DISABLED("Das Plugin wurde deaktiviert.", MessageLevel.SUCCESS),
  PLUGIN_ENABLED("Das Plugin wurde aktiviert.", MessageLevel.SUCCESS);

  private final String rawMessage;
  private final MessageLevel messageLevel;

  Message(final String rawMessage, final MessageLevel messageLevel) {
    this.rawMessage = rawMessage;
    this.messageLevel = messageLevel;
  }
}