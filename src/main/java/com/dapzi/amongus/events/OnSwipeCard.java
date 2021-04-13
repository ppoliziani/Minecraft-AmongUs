package com.dapzi.amongus.events;

import com.dapzi.amongus.Main;
import com.dapzi.amongus.tasks.SwipeCardTask;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

public class OnSwipeCard implements Listener {

    private Main plugin;
    BukkitTask swipeTask = null;

    public OnSwipeCard(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onReport(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();

        if (action == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType().equals(Material.OBSIDIAN)) {
            Inventory swipeCardGUI = Bukkit.createInventory(player, 9, "Swipe Card");
            //swipeCardGUI.setItem(0, new ItemStack(Material.QUARTZ_BLOCK, 9));
            player.getInventory().setItem(9, new ItemStack(Material.QUARTZ_BLOCK, 9));
            player.openInventory(swipeCardGUI);

            this.swipeTask = new SwipeCardTask(plugin, player, swipeCardGUI).runTaskTimer(plugin, 0, 10);

        }

    }
}
