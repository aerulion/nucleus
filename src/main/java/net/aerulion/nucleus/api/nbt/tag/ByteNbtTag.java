package net.aerulion.nucleus.api.nbt.tag;

import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
@ToString(callSuper = true)
public class ByteNbtTag extends NbtTag {

    protected final @NotNull byte value;

    public ByteNbtTag(@Nullable String key, @NotNull byte value) {
        super(key);
        this.value = value;
    }
}