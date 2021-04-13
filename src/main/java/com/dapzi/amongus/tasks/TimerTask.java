package com.dapzi.amongus.tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class TimerTask extends BukkitRunnable implements Listener {

    private static int time;

    public TimerTask(int time) {
        this.time = time;
    }

    @Override
    public void run() {
        Bukkit.broadcastMessage(ChatColor.AQUA + String.valueOf(time));
        time--;
        if (time < 1) {
            this.cancel();
        }
    }
}
