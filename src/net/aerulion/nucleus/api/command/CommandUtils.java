package net.aerulion.nucleus.api.command;

import java.util.ArrayList;
import java.util.Iterator;

public class CommandUtils {
    public static ArrayList<String> filterForTabCompleter(ArrayList<String> commandList, String filter) {
        if (filter != null) {
            for (Iterator<String> iterator = commandList.iterator(); iterator.hasNext(); ) {
                String value = iterator.next();
                if (!value.toLowerCase().startsWith(filter.toLowerCase())) {
                    {
                        iterator.remove();
                    }
                }
            }
        }
        return commandList;
    }
}