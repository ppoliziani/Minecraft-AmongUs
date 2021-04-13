package com.dapzi.amongus.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class OnHunger implements Listener {
    @EventHandler
    public void onHungerDeplete(FoodLevelChangeEvent e) {
        Bukkit.getServer().broadcastMessage("Hunger change detected but no hunger lost");
        e.setCancelled(true);
        e.setFoodLevel(20);
    }
}
