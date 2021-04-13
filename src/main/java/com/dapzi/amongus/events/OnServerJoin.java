package com.dapzi.amongus.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.UUID;

public class OnServerJoin implements Listener {

    public static HashMap<UUID, String> playerMap = new HashMap<UUID, String>();

    @EventHandler
    public void onServerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        playerMap.put(playerUUID, player.getDisplayName());
    }

}
