package net.aerulion.nucleus.api.nbt.tag;

import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
@ToString(callSuper = true)
public class LongArrayNbtTag extends NbtTag {

  protected final @NotNull long[] value;

  public LongArrayNbtTag(@Nullable String key, @NotNull long[] value) {
    super(key);
    this.value = value;
  }
}