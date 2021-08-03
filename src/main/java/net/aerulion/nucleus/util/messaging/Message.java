package net.aerulion.nucleus.util.messaging;

import lombok.Getter;
import net.aerulion.nucleus.api.color.Color;
import net.aerulion.nucleus.api.messaging.MessageLevel;
import net.aerulion.nucleus.api.messaging.Placeholder;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;

@Getter
class Message {

  private final @NotNull Component message;
  private final MessageLevel messageLevel;
  private final Audience recipient;

  public Message(String message, MessageLevel messageLevel, Audience recipient,
      Placeholder... placeholders) {
    this.messageLevel = messageLevel;
    this.message = formatString(message, placeholders);
    this.recipient = recipient;
  }

  private @NotNull Component formatString(String message, Placeholder @NotNull ... placeholders) {
    for (@NotNull Placeholder placeholder : placeholders) {
      message = message.replaceAll(placeholder.getPlaceholder(), placeholder.getReplacement());
    }
    @NotNull Component component = Component.text("[")
        .color(Color.TEXT)
        .append(Component.text(messageLevel.getPrefix())
            .color(messageLevel.getPrefixColor())
            .decorate(TextDecoration.BOLD))
        .append(Component.text("] ")
            .color(Color.TEXT)
            .decoration(TextDecoration.BOLD, TextDecoration.State.FALSE));
    boolean isAccent = message.startsWith("**");
    for (@NotNull String s : message.split("\\*\\*")) {
      if (s.equals("")) {
        continue;
      }
      component = component.append(
          Component.text(s)
              .color(isAccent ? messageLevel.getAccentColor() : messageLevel.getTextColor())
              .decoration(TextDecoration.BOLD, isAccent ?
                  TextDecoration.State.TRUE : TextDecoration.State.FALSE));
      isAccent = !isAccent;
    }
    return component;
  }

  protected void playSound() {
    recipient.playSound(Sound.sound(
        messageLevel.getSoundType().getSound().key(),
        Sound.Source.MASTER,
        messageLevel.getSoundType().getVolume(),
        messageLevel.getSoundType().getPitch()));
  }
}