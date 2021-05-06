package net.aerulion.nucleus.api.messaging;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
@AllArgsConstructor
public class Placeholder {
    private final @NotNull String placeholder;
    private final @NotNull String replacement;
}