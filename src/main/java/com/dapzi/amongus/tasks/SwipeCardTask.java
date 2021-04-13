package com.dapzi.amongus.tasks;

import com.dapzi.amongus.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Lever;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;
import java.util.Random;

public class SwipeCardTask extends BukkitRunnable implements Listener {

    private Main plugin;
    private Player player;
    private Inventory gui;

    public SwipeCardTask(Main plugin, Player player, Inventory gui) {
        this.plugin = plugin;
        this.player = player;
        this.gui = gui;
    }

    Random rand = new Random();

    @Override
    public void run() {
        int count = 0;
        int num = rand.nextInt(10);
        /*
        long elaspedTime = 0;
        if (Objects.requireNonNull(gui.getItem(0)).getType() == Material.QUARTZ_BLOCK) {
            elaspedTime = System.nanoTime();
            player.sendMessage("FIRST BLOCK " + String.valueOf(elaspedTime));
        }
        if (Objects.requireNonNull(gui.getItem(8)).getType() == Material.QUARTZ_BLOCK) {
            long newtime = System.nanoTime();
            player.sendMessage("LAST BLOCK " + String.valueOf(newtime));
            elaspedTime = System.nanoTime() - elaspedTime;
        }
         */

        for (ItemStack is : gui) {
            if (is.getType().equals(Material.QUARTZ_BLOCK)) {
                count++;
                if (count == 9) {
                    player.sendMessage(ChatColor.GREEN + "Swipe Card Completed");
                    //player.sendMessage(ChatColor.BLUE + String.valueOf(elaspedTime/1000000));
                    player.closeInventory();
                    this.cancel();
                }
            }
        }
    }
}
