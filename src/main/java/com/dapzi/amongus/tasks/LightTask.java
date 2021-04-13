package com.dapzi.amongus.tasks;

import com.dapzi.amongus.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.data.Powerable;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.material.Lever;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class LightTask extends BukkitRunnable implements Listener {

    private Main plugin;
    private Player player;

    public LightTask(Main plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
    }

    @Override
    public void run() {
        //player.removePotionEffect(PotionEffectType.NIGHT_VISION);
        Block block = player.getWorld().getBlockAt(-25, 74, 183);
        player.sendMessage(ChatColor.LIGHT_PURPLE + "Block is: " + block.getType());

        Lever lever = (Lever) block.getState().getData();
        player.sendMessage(String.valueOf(lever.isPowered()));
        if (lever.isPowered()) {
            player.sendMessage("LIGHTS SHOULD BE OFF");
            //remove blindness
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.removePotionEffect(PotionEffectType.BLINDNESS);
                //p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION , 30 * 20, 1));
            }
            cancel();
        }

    }
}
