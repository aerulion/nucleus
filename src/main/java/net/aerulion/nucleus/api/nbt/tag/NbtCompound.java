package net.aerulion.nucleus.api.nbt.tag;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
@ToString(callSuper = true)
public class NbtCompound extends NbtTag {

  protected final @NotNull Map<String, NbtTag> children;

  public NbtCompound(final @Nullable String key, final @NotNull Map<String, NbtTag> children) {
    super(key);
    this.children = children;
  }

  public NbtCompound(final @Nullable String key) {
    this(key, new HashMap<>());
  }

  public @NotNull NbtCompound put(final @NotNull String key, final @NotNull NbtTag value) {
    children.put(key, value);
    return this;
  }

  public @NotNull NbtCompound remove(final @NotNull String key) {
    children.remove(key);
    return this;
  }

  public @NotNull NbtCompound clear() {
    children.clear();
    return this;
  }

  public boolean containsKey(final @Nullable String key) {
    return children.containsKey(key);
  }
}