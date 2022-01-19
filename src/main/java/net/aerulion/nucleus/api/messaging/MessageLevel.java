package net.aerulion.nucleus.api.messaging;

import lombok.Getter;
import net.aerulion.nucleus.api.color.Color;
import net.aerulion.nucleus.api.sound.SoundType;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.format.TextColor;

@Getter
public enum MessageLevel {
  ERROR(Color.ERROR, Color.TEXT, Color.ERROR, BossBar.Color.RED, SoundType.ERROR, "!"),
  INFO(Color.TEXT, Color.INFO, Color.INFO, BossBar.Color.WHITE, SoundType.INFO, "i"),
  SUCCESS(Color.SUCCESS, Color.TEXT, Color.SUCCESS, BossBar.Color.GREEN, SoundType.SUCCESS, "i"),
  WARNING(Color.TEXT, Color.WARNING, Color.WARNING, BossBar.Color.YELLOW, SoundType.WARNING, "!");

  private final TextColor textColor;
  private final TextColor accentColor;
  private final TextColor prefixColor;
  private final BossBar.Color bossBarColor;
  private final SoundType soundType;
  private final String prefix;

  MessageLevel(final TextColor textColor, final TextColor accentColor, final TextColor prefixColor,
      final BossBar.Color bossBarColor, final SoundType soundType, final String prefix) {
    this.textColor = textColor;
    this.accentColor = accentColor;
    this.prefixColor = prefixColor;
    this.bossBarColor = bossBarColor;
    this.soundType = soundType;
    this.prefix = prefix;
  }
}