package net.aerulion.nucleus.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public enum Messages {
    CONSOLE_CONNECTION_POOL_INITIALIZED(Component.text("Der ConnectionPool wurde initialisiert. Dauerte %timestamp%ms").color(NamedTextColor.GREEN)),
    CONSOLE_CONNECTION_POOL_INITIALIZING(Component.text("Initialisiere ConnectionPool...").color(NamedTextColor.GREEN)),
    CONSOLE_DISABLING(Component.text("Deaktiviere Plugin...").color(NamedTextColor.GREEN)),
    CONSOLE_ENABLING(Component.text("Aktiviere Plugin...").color(NamedTextColor.GREEN)),
    CONSOLE_ERROR_DEFAULT_MYSQL_CONFIG(Component.text("Die Default MySQL Config Datei konnte nicht erstellt werden.").color(NamedTextColor.RED)),
    CONSOLE_PLUGIN_DISABLED(Component.text("Das Plugin wurde deaktiviert.").color(NamedTextColor.GREEN)),
    CONSOLE_PLUGIN_ENABLED(Component.text("Das Plugin wurde aktiviert.").color(NamedTextColor.GREEN)),
    PREFIX(Component.text("[").color(NamedTextColor.GRAY).append(Component.text("Nucleus").color(NamedTextColor.AQUA).decorate(TextDecoration.BOLD)).append(Component.text("] ").color(NamedTextColor.GRAY)));

    private final Component message;

    Messages(Component message) {
        this.message = message;
    }

    public Component get() {
        return PREFIX.getRaw().append(message);
    }

    public Component getRaw() {
        return message;
    }
}