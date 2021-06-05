package net.aerulion.nucleus.api.nbt.tag;

import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Getter
@ToString(callSuper = true)
public class NbtCompound extends NbtTag {

    protected final @NotNull List<NbtTag> children;

    public NbtCompound(@Nullable String key, @NotNull List<NbtTag> children) {
        super(key);
        this.children = children;
    }
}