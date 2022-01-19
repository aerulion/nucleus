package net.aerulion.nucleus.api.sound;

import java.util.List;
import lombok.experimental.UtilityClass;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.sound.Sound;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * A utility class for sound related methods
 */
@UtilityClass
public final class SoundUtils {

  /**
   * Plays a sound to the specified audience emitted from a specified location
   *
   * @param audience  the target audience
   * @param location  the emitting location
   * @param soundType the SoundType to play
   */
  public static void playSound(final @NotNull Audience audience, final @NotNull Location location,
      final @NotNull SoundType soundType) {
    audience.playSound(
        Sound.sound(soundType.getSound().key(), Sound.Source.MASTER, soundType.getPitch(),
            soundType.getVolume()), location.getX(), location.getY(), location.getZ());
  }

  /**
   * Plays a sound to the specified audience
   *
   * @param audience  the target audience
   * @param soundType the SoundType to play
   */
  public static void playSound(final @NotNull Audience audience,
      final @NotNull SoundType soundType) {
    audience.playSound(
        Sound.sound(soundType.getSound().key(), Sound.Source.MASTER, soundType.getPitch(),
            soundType.getVolume()));
  }

  /**
   * Plays a sound to all online players
   *
   * @param soundType the SoundType to play
   */
  public static void playSoundToAllPlayers(final @NotNull SoundType soundType) {
    for (final @NotNull Player player : Bukkit.getOnlinePlayers()) {
      playSound(player, soundType);
    }
  }

  /**
   * Plays a chime consisting of multiple notes to the audience
   *
   * @param audience the target audience
   * @param delay    the delay before the sound starts
   * @param period   the delay between each note
   * @param sound    the sound to play
   * @param volume   the volume of the chime
   * @param pitches  the pitch values of the chime
   */
  public static void playMultipleNotes(final Audience audience, final long delay, final long period,
      final org.bukkit.@NotNull Sound sound, final float volume, final List<Float> pitches) {
    new SoundTask(audience, delay, period, sound.key(), volume, pitches);
  }

  /**
   * Plays a chime consisting of multiple notes to all online players
   *
   * @param delay   the delay before the sound starts
   * @param period  the delay between each note
   * @param sound   the sound to play
   * @param volume  the volume of the chime
   * @param pitches the pitch values of the chime
   */
  public static void playMultipleNotesToAllPlayers(final long delay, final long period,
      final org.bukkit.@NotNull Sound sound, final float volume, final List<Float> pitches) {
    for (final Player player : Bukkit.getOnlinePlayers()) {
      playMultipleNotes(player, delay, period, sound, volume, pitches);
    }
  }
}