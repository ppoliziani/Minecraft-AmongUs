package com.dapzi.amongus.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class OnReport implements Listener {

    @EventHandler
    public void onReport(PlayerInteractEvent event) {
        //Player player = event.getPlayer();
        //event.getPlayer().sendMessage("You made and interaction");
        Action action = event.getAction();
        if (action == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType().toString().endsWith("WOOL") ) {
            Bukkit.getServer().broadcastMessage(ChatColor.RED + "A body has been reported");
            for (Player player : Bukkit.getOnlinePlayers()) {
                double x = player.getLocation().getX();
                double z = player.getLocation().getZ();
                double y = player.getLocation().getY();

                // CAF CENTER = (x, y, z) --> ()
                player.teleport(new Location(player.getWorld(), x, y + 5, z));
            }
        }

    }
}
