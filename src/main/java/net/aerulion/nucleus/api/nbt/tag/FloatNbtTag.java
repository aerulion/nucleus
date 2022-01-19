package net.aerulion.nucleus.api.nbt.tag;

import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
@ToString(callSuper = true)
public class FloatNbtTag extends NbtTag {

  protected final float value;

  public FloatNbtTag(final @Nullable String key, final float value) {
    super(key);
    this.value = value;
  }
}