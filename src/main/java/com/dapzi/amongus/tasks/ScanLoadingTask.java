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

public class ScanLoadingTask extends BukkitRunnable implements Listener {

    private Main plugin;
    private Player player;
    private Inventory scanGUI;
    private int slot;

    public ScanLoadingTask(Main plugin, Player player, Inventory scanGUI, int slot) {
        this.plugin = plugin;
        this.player = player;
        this.scanGUI = scanGUI;
        this.slot = slot;
    }

    @Override
    public void run() {
        if (slot >= 8) {
            this.cancel();
        }
        player.openInventory(scanGUI).setItem(slot, new ItemStack(Material.GREEN_WOOL));
        slot++;
    }
}
