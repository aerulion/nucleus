package net.aerulion.nucleus.api.nbt.tag;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
@ToString(callSuper = true)
public class NbtList extends NbtTag {

  protected final @NotNull List<NbtTag> content;

  public NbtList(final @Nullable String key, final @NotNull List<NbtTag> content) {
    super(key);
    this.content = content;
  }

  public NbtList(final @Nullable String key) {
    this(key, new ArrayList<>());
  }

  public @NotNull NbtList add(final @NotNull NbtTag value) {
    content.add(value);
    return this;
  }

  public @NotNull NbtList remove(final @NotNull NbtTag nbtTag) {
    content.remove(nbtTag);
    return this;
  }

  public @NotNull NbtList clear() {
    content.clear();
    return this;
  }

  public boolean contains(final @Nullable NbtTag value) {
    return content.contains(value);
  }
}