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

  public NbtCompound(@Nullable String key, @NotNull Map<String, NbtTag> children) {
    super(key);
    this.children = children;
  }

  public NbtCompound(@Nullable String key) {
    this(key, new HashMap<>());
  }

  public @NotNull NbtCompound put(@NotNull String key, @NotNull NbtTag value) {
    children.put(key, value);
    return this;
  }

  public @NotNull NbtCompound remove(@NotNull String key) {
    children.remove(key);
    return this;
  }

  public @NotNull NbtCompound clear() {
    children.clear();
    return this;
  }

  public boolean containsKey(@Nullable String key) {
    return children.containsKey(key);
  }
}