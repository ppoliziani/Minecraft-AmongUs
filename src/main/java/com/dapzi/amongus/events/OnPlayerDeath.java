package com.dapzi.amongus.events;

import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class OnPlayerDeath implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity().getPlayer();
        assert player != null;
        player.getServer().broadcastMessage(player.getDisplayName() + " killed themselves");
        Location location = player.getLocation();
        ItemStack body = player.getInventory().getItem(5);
        location.getBlock().setType(body.getType());
        player.setGameMode(GameMode.SPECTATOR);
    }

}
