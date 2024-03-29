package net.aerulion.nucleus.api.nbt.tag;

import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
@ToString(callSuper = true)
public class ByteNbtTag extends NbtTag {

  protected final byte value;

  public ByteNbtTag(final @Nullable String key, final byte value) {
    super(key);
    this.value = value;
  }
}