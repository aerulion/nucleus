package net.aerulion.nucleus.api.nbt.tag;

import lombok.Getter;
import lombok.ToString;
import net.aerulion.nucleus.api.uuid.UUIDUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

@Getter
@ToString(callSuper = true)
public class IntArrayNbtTag extends NbtTag {

    protected final @NotNull int[] value;

    public IntArrayNbtTag(@Nullable String key, @NotNull int[] value) {
        super(key);
        this.value = value;
    }

    public IntArrayNbtTag(@Nullable String key, @NotNull UUID uuid) {
        this(key, UUIDUtils.toIntArray(uuid));
    }

    public UUID asUUID() {
        return UUIDUtils.fromIntArray(value);
    }
}