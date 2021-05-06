package net.aerulion.nucleus.api.messaging;

import net.aerulion.nucleus.Main;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public class Messenger {

    public static void bossBar(Audience recipient, String message, MessageLevel messageLevel, int seconds, Placeholder... placeholders) {
        Main.messagingManager.bossBarMessage(message, messageLevel, recipient, seconds, placeholders);
    }

    public static void bossBar(Audience recipient, String message, MessageLevel messageLevel, Placeholder... placeholders) {
        Main.messagingManager.bossBarMessage(message, messageLevel, recipient, 5, placeholders);
    }

    public static void bossBar(Audience recipient, String message, Placeholder... placeholders) {
        Main.messagingManager.bossBarMessage(message, MessageLevel.INFO, recipient, 5, placeholders);
    }

    public static void bossBar(Audience recipient, String message, NamespacedKey key, MessageLevel messageLevel, int seconds, Placeholder... placeholders) {
        Main.messagingManager.keyedBossBarMessage(message, messageLevel, recipient, seconds, key, placeholders);
    }

    public static void bossBar(Audience recipient, String message, NamespacedKey key, MessageLevel messageLevel, Placeholder... placeholders) {
        Main.messagingManager.keyedBossBarMessage(message, messageLevel, recipient, 5, key, placeholders);
    }

    public static void bossBar(Audience recipient, String message, NamespacedKey key, Placeholder... placeholders) {
        Main.messagingManager.keyedBossBarMessage(message, MessageLevel.INFO, recipient, 5, key, placeholders);
    }

    public static void chat(Audience recipient, String message, MessageLevel messageLevel, Placeholder... placeholders) {
        Main.messagingManager.chat(recipient, message, messageLevel, placeholders);
    }

    public static void chat(Audience recipient, String message, Placeholder... placeholders) {
        Main.messagingManager.chat(recipient, message, MessageLevel.INFO, placeholders);
    }

    public static @NotNull Component format(String message, MessageLevel messageLevel, Placeholder... placeholders) {
        return Main.messagingManager.format(message, messageLevel, placeholders);
    }
}