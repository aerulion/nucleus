package net.aerulion.nucleus.api.command;

import java.util.List;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

/**
 * A utility class for handling commands
 */
@UtilityClass
public final class CommandUtils {

  /**
   * Filters a list by a given filter
   *
   * @param commandList The list to be filtered
   * @param filter      A string used as a filter
   * @return The filtered list
   */
  public static @NotNull List<String> filterForTabCompleter(
      @NotNull List<String> commandList, @NotNull String filter) {
    commandList.removeIf(value -> !value.toLowerCase().startsWith(filter.toLowerCase()));
    return commandList;
  }
}