package net.aerulion.nucleus.api.sound;

import org.bukkit.Sound;

public enum SoundType {
  @Deprecated
  ALERT(Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.8F, 1.5F),
  ERROR(Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 0.8F, 1.5F),
  INFO(Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.8F, 1.5F),
  ITEM_PICKUP(Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F),
  OPEN_CONTAINER(Sound.BLOCK_CHEST_OPEN, 0.8F, 1.3F),
  SUCCESS(Sound.ENTITY_PLAYER_LEVELUP, 0.8F, 1.5F),
  UI_CLICK(Sound.UI_BUTTON_CLICK, 1.0F, 1.0F),
  UI_MOVING(Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1.0F, 1.5F),
  WARNING(Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.8F, 1.5F);

  private final Sound sound;
  private final float volume;
  private final float pitch;

  SoundType(Sound sound, float volume, float pitch) {
    this.sound = sound;
    this.volume = volume;
    this.pitch = pitch;
  }

  public Sound getSound() {
    return sound;
  }

  public float getPitch() {
    return pitch;
  }

  public float getVolume() {
    return volume;
  }
}