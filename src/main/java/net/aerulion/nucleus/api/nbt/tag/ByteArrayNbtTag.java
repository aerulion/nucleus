package net.aerulion.nucleus.api.nbt.tag;

import java.util.UUID;
import lombok.Getter;
import lombok.ToString;
import net.aerulion.nucleus.api.uuid.UUIDUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
@ToString(callSuper = true)
public class ByteArrayNbtTag extends NbtTag {

  protected final byte[] value;

  public ByteArrayNbtTag(final @Nullable String key, final byte[] value) {
    super(key);
    this.value = value;
  }

  public ByteArrayNbtTag(final @Nullable String key, final @NotNull UUID uuid) {
    this(key, UUIDUtils.toByteArray(uuid));
  }

  public @NotNull UUID asUUID() {
    return UUIDUtils.fromByteArray(value);
  }
}