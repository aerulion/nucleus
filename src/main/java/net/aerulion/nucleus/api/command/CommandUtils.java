package net.aerulion.nucleus.api.command;

import java.util.ArrayList;

public class CommandUtils {
    public static ArrayList<String> filterForTabCompleter(ArrayList<String> commandList, String filter) {
        if (filter != null)
            commandList.removeIf(value -> !value.toLowerCase().startsWith(filter.toLowerCase()));
        return commandList;
    }
}