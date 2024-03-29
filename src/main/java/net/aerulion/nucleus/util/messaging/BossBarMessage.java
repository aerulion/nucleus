package net.aerulion.nucleus.util.messaging;

import lombok.Getter;
import net.aerulion.nucleus.api.messaging.MessageLevel;
import net.aerulion.nucleus.api.messaging.Placeholder;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

@Getter
class BossBarMessage extends Message {

  private final @NotNull BossBar bossBar;
  private final int seconds;
  private int remainingSeconds;

  public BossBarMessage(final String message, final MessageLevel messageLevel,
      final Audience recipient, final int seconds, final Placeholder... placeholders) {
    super(message, messageLevel, recipient, placeholders);
    this.seconds = seconds;
    this.remainingSeconds = seconds;
    this.bossBar = BossBar.bossBar(getMessage(), 1F, getMessageLevel().getBossBarColor(),
        BossBar.Overlay.PROGRESS);
  }

  public void show() {
    getRecipient().showBossBar(this.bossBar);
    playSound();
  }

  public void tickDown() {
    remainingSeconds--;
    if (remainingSeconds == 0) {
      getRecipient().hideBossBar(this.bossBar);
      return;
    }
    bossBar.progress(Math.min(1F, Math.max(0F, remainingSeconds / (float) seconds)));
  }

  public boolean isInvalid() {
    return remainingSeconds <= 0;
  }

  public void reset(final @NotNull Component component) {
    reset();
    getBossBar().name(component);
  }

  public void reset() {
    remainingSeconds = seconds;
    bossBar.progress(1);
  }
}