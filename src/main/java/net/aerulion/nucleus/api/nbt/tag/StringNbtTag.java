package net.aerulion.nucleus.api.nbt.tag;

import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
@ToString(callSuper = true)
public class StringNbtTag extends NbtTag {

    protected final @NotNull String value;

    public StringNbtTag(@Nullable String key, @NotNull String value) {
        super(key);
        this.value = value;
    }
}