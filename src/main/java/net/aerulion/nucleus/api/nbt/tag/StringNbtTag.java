package net.aerulion.nucleus.api.nbt.tag;

import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

@Getter
@ToString(callSuper = true)
public class StringNbtTag extends NbtTag {

    protected final @NotNull String value;

    public StringNbtTag(@Nullable String key, @NotNull String value) {
        super(key);
        this.value = value;
    }

    public StringNbtTag(@Nullable String key, @NotNull UUID uuid) {
        this(key, uuid.toString());
    }

    public UUID asUUID() {
        return UUID.fromString(value);
    }
}