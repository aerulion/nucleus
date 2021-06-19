package net.aerulion.nucleus.api.nbt.tag;

import lombok.Getter;
import lombok.ToString;
import net.aerulion.nucleus.api.uuid.UUIDUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

@Getter
@ToString(callSuper = true)
public class ByteArrayNbtTag extends NbtTag {

    protected final @NotNull byte[] value;

    public ByteArrayNbtTag(@Nullable String key, @NotNull byte[] value) {
        super(key);
        this.value = value;
    }

    public ByteArrayNbtTag(@Nullable String key, @NotNull UUID uuid) {
        this(key, UUIDUtils.toByteArray(uuid));
    }

    public @NotNull UUID asUUID() {
        return UUIDUtils.fromByteArray(value);
    }
}