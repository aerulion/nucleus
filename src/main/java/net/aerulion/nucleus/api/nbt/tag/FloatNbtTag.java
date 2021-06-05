package net.aerulion.nucleus.api.nbt.tag;

import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
@ToString(callSuper = true)
public class FloatNbtTag extends NbtTag{

    protected final @NotNull float value;

    public FloatNbtTag(@Nullable String key, @NotNull float value) {
        super(key);
        this.value = value;
    }
}