package net.aerulion.nucleus.api.nbt.tag;

import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
@ToString(callSuper = true)
public class LongNbtTag extends NbtTag {

  protected final long value;

  public LongNbtTag(final @Nullable String key, final long value) {
    super(key);
    this.value = value;
  }
}