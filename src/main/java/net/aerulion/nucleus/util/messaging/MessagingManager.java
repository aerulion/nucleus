package net.aerulion.nucleus.util.messaging;

import net.aerulion.nucleus.Main;
import net.aerulion.nucleus.api.messaging.MessageLevel;
import net.aerulion.nucleus.api.messaging.Placeholder;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessagingManager extends BukkitRunnable {

    private final List<BossBarMessage> bossBarMessages = new ArrayList<>();
    private final HashMap<NamespacedKey, KeyedBossBarMessage> keyedBossBarMessages = new HashMap<>();

    public MessagingManager() {
        this.runTaskTimer(Main.plugin, 20L, 20L);
    }

    private void bossBarMessage(BossBarMessage bossBarMessage) {
        if (bossBarMessage instanceof KeyedBossBarMessage) {
            KeyedBossBarMessage keyedBossBarMessage = (KeyedBossBarMessage) bossBarMessage;
            if (keyedBossBarMessages.containsKey(keyedBossBarMessage.getKey()))
                keyedBossBarMessages.get(keyedBossBarMessage.getKey()).reset(keyedBossBarMessage.getMessage());
            else {
                keyedBossBarMessages.put(keyedBossBarMessage.getKey(), keyedBossBarMessage);
                keyedBossBarMessage.show();
            }
        } else {
            bossBarMessages.add(bossBarMessage);
            bossBarMessage.show();
        }
    }

    public void bossBarMessage(String message, MessageLevel messageLevel, Audience recipient, int seconds, Placeholder... placeholders) {
        bossBarMessage(new BossBarMessage(message, messageLevel, recipient, seconds, placeholders));
    }

    public void keyedBossBarMessage(String message, MessageLevel messageLevel, Audience recipient, int seconds, NamespacedKey key, Placeholder... placeholders) {
        bossBarMessage(new KeyedBossBarMessage(message, messageLevel, recipient, seconds, key, placeholders));
    }

    public void chat(Audience recipient, String message, MessageLevel messageLevel, Placeholder... placeholders) {
        new ChatMessage(message, messageLevel, recipient, placeholders).send();
    }

    public Component format(String message, MessageLevel messageLevel, Placeholder... placeholders) {
        return new Message(message, messageLevel, null, placeholders).getMessage();
    }

    @Override
    public void run() {
        bossBarMessages.removeIf(BossBarMessage::isInvalid);
        keyedBossBarMessages.values().removeIf(KeyedBossBarMessage::isInvalid);
        bossBarMessages.forEach(BossBarMessage::tickDown);
        keyedBossBarMessages.values().forEach(KeyedBossBarMessage::tickDown);
    }
}