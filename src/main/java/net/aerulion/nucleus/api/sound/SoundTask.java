package net.aerulion.nucleus.api.sound;

import net.aerulion.nucleus.Main;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class SoundTask extends BukkitRunnable {

    private int timesExecuted;
    private final Audience audience;
    private final Key soundKey;
    private final float volume;
    private final List<Float> pitches;

    public SoundTask(Audience audience, long delay, long period, Key soundKey, float volume, List<Float> pitches) {
        this.timesExecuted = 0;
        this.audience = audience;
        this.soundKey = soundKey;
        this.volume = volume;
        this.pitches = pitches;
        this.runTaskTimerAsynchronously(Main.plugin, delay, period);
    }

    @Override
    public void run() {
        if (timesExecuted >= pitches.size()) {
            this.cancel();
        } else {
            audience.playSound(Sound.sound(soundKey, Sound.Source.MASTER, volume, pitches.get(timesExecuted)));
            timesExecuted++;
        }
    }
}