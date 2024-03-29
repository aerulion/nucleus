package net.aerulion.nucleus.util.messaging;

import net.aerulion.nucleus.api.messaging.MessageLevel;
import net.aerulion.nucleus.api.messaging.Placeholder;
import net.kyori.adventure.audience.Audience;

public class ChatMessage extends Message {

  public ChatMessage(final String message, final MessageLevel messageLevel,
      final Audience recipient, final Placeholder... placeholders) {
    super(message, messageLevel, recipient, placeholders);
  }

  public void send() {
    getRecipient().sendMessage(getMessage());
    playSound();
  }
}