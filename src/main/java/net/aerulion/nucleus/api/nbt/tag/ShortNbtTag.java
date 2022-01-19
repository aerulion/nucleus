package net.aerulion.nucleus.api.nbt.tag;

import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
@ToString(callSuper = true)
public class ShortNbtTag extends NbtTag {

  protected final short value;

  public ShortNbtTag(final @Nullable String key, final short value) {
    super(key);
    this.value = value;
  }
}