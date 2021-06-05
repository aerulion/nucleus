package net.aerulion.nucleus.api.nbt.tag;

import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Getter
@ToString(callSuper = true)
public class NbtList extends NbtTag {

    protected final @NotNull List<NbtTag> content;

    public NbtList(@Nullable String key, @NotNull List<NbtTag> content) {
        super(key);
        this.content = content;
    }
}