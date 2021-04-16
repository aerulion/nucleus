package net.aerulion.nucleus.api.messaging;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Placeholder {
    private final String placeholder;
    private final String replacement;
}