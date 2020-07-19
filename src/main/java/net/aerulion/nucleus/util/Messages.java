package net.aerulion.nucleus.util;

public enum Messages {
    CONSOLE_CONNECTION_POOL_INITIALIZED("§aDer ConnectionPool wurde initialisiert. Dauerte §e"),
    CONSOLE_CONNECTION_POOL_INITIALIZING("§aInitialisiere ConnectionPool..."),
    CONSOLE_DISABLING("§aDeaktiviere Plugin..."),
    CONSOLE_ENABLING("§aAktiviere Plugin..."),
    CONSOLE_ERROR_DEFAULT_MYSQL_CONFIG("§aDie Default MySQL Config Datei konnte nicht erstellt werden."),
    CONSOLE_PLUGIN_DISABLED("§aDas Plugin wurde deaktiviert."),
    CONSOLE_PLUGIN_ENABLED("§aDas Plugin wurde aktiviert.");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String get() {
        return "§7[§b§lNucleus§7] " + message;
    }

    public String getRaw() {
        return message;
    }
}