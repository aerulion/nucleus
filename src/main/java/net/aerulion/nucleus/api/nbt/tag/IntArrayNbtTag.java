package net.aerulion.nucleus.api.nbt.tag;

import java.util.UUID;
import lombok.Getter;
import lombok.ToString;
import net.aerulion.nucleus.api.uuid.UUIDUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
@ToString(callSuper = true)
public class IntArrayNbtTag extends NbtTag {

  protected final int[] value;

  public IntArrayNbtTag(final @Nullable String key, final int[] value) {
    super(key);
    this.value = value;
  }

  public IntArrayNbtTag(final @Nullable String key, final @NotNull UUID uuid) {
    this(key, UUIDUtils.toIntArray(uuid));
  }

  public @NotNull UUID asUUID() {
    return UUIDUtils.fromIntArray(value);
  }
}