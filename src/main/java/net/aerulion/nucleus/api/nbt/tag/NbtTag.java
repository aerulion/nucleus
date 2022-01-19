package net.aerulion.nucleus.api.nbt.tag;

import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

@Getter
@ToString
public abstract class NbtTag {

  protected final @Nullable String key;

  protected NbtTag(final @Nullable String key) {
    this.key = key;
  }
}