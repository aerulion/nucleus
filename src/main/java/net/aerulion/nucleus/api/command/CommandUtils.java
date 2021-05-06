package net.aerulion.nucleus.api.command;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * A utility class for handling commands
 */
@UtilityClass
public final class CommandUtils {

    /**
     * Filters an list by an given filter
     *
     * @param commandList The list to be filtered
     * @param filter      An string used as a filter
     * @return The filtered list
     */
    public static @NotNull ArrayList<String> filterForTabCompleter(@NotNull ArrayList<String> commandList, @NotNull String filter) {
        commandList.removeIf(value -> !value.toLowerCase().startsWith(filter.toLowerCase()));
        return commandList;
    }
}