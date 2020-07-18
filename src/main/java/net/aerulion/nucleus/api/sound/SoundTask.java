package net.aerulion.nucleus.api.sound;

import net.aerulion.nucleus.Main;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class SoundTask extends BukkitRunnable {

    private int timesExecuted;
    private final Player player;
    private final Sound sound;
    private final float volume;
    private final List<Float> pitches;

    public SoundTask(Player player, long delay, long period, Sound sound, float volume, List<Float> pitches) {
        this.timesExecuted = 0;
        this.player = player;
        this.sound = sound;
        this.volume = volume;
        this.pitches = pitches;
        this.runTaskTimerAsynchronously(Main.plugin, delay, period);
    }

    @Override
    public void run() {
        if (timesExecuted >= pitches.size()) {
            this.cancel();
        } else {
            player.playSound(player.getLocation(), sound, volume, pitches.get(timesExecuted));
            timesExecuted++;
        }
    }
}