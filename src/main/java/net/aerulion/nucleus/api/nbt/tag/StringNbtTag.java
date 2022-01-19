package net.aerulion.nucleus.api.nbt.tag;

import java.util.UUID;
import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
@ToString(callSuper = true)
public class StringNbtTag extends NbtTag {

  protected final @NotNull String value;

  public StringNbtTag(final @Nullable String key, final @NotNull String value) {
    super(key);
    this.value = value;
  }

  public StringNbtTag(final @Nullable String key, final @NotNull UUID uuid) {
    this(key, uuid.toString());
  }

  public @NotNull UUID asUUID() {
    return UUID.fromString(value);
  }
}