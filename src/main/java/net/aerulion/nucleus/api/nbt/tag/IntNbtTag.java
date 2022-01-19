package net.aerulion.nucleus.api.nbt.tag;

import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
@ToString(callSuper = true)
public class IntNbtTag extends NbtTag {

  protected final int value;

  public IntNbtTag(final @Nullable String key, final int value) {
    super(key);
    this.value = value;
  }
}