package net.aerulion.nucleus.api.nbt.tag;

import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
@ToString(callSuper = true)
public class IntNbtTag extends NbtTag {

  protected final @NotNull int value;

  public IntNbtTag(@Nullable String key, @NotNull int value) {
    super(key);
    this.value = value;
  }
}