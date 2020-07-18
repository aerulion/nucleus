package net.aerulion.nucleus.api.sound;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SoundUtils {

    public static void playSound(Player player, SoundType soundType) {
        player.playSound(player.getLocation(), soundType.getSound(), soundType.getVolume(), soundType.getPitch());
    }

    public static void playSound(CommandSender commandSender, SoundType soundType) {
        if (commandSender instanceof Player)
            playSound((Player) commandSender, soundType);
    }

    public static void playSoundToAllPlayers(SoundType soundType) {
        for (Player player : Bukkit.getOnlinePlayers())
            playSound(player, soundType);
    }

    public static void playMultipleNotes(Player player, long delay, long period, Sound sound, float volume, List<Float> pitches) {
        new SoundTask(player, delay, period, sound, volume, pitches);
    }

    public static void playMultipleNotesToAllPlayers(long delay, long period, Sound sound, float volume, List<Float> pitches) {
        for (Player player : Bukkit.getOnlinePlayers())
            playMultipleNotes(player, delay, period, sound, volume, pitches);
    }
}