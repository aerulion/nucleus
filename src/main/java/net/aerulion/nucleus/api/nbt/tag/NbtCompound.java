package net.aerulion.nucleus.api.nbt.tag;

import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

@Getter
@ToString(callSuper = true)
public class NbtCompound extends NbtTag {

    protected final @NotNull HashMap<String, NbtTag> children;

    public NbtCompound(@Nullable String key, @NotNull HashMap<String, NbtTag> children) {
        super(key);
        this.children = children;
    }

    public NbtCompound(@Nullable String key) {
        super(key);
        this.children = new HashMap<>();
    }
}