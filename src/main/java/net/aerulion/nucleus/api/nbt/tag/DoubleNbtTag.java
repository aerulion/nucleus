package net.aerulion.nucleus.api.nbt.tag;

import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
@ToString(callSuper = true)
public class DoubleNbtTag extends NbtTag {

  protected final double value;

  public DoubleNbtTag(final @Nullable String key, final double value) {
    super(key);
    this.value = value;
  }
}