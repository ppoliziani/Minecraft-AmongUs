package com.dapzi.amongus.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

public class OnInventoryClick implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equals("DAN")) {

            switch(e.getCurrentItem().getType()){
                case STONE:
                    player.sendMessage("Clicked STONE");
                    player.closeInventory();
                    break;
            }
            e.setCancelled(true);
        }

        if (e.getView().getTitle().equals("Swipe Card")) {
            int count = 0;
            while (count < 9) {
                for (ItemStack is : e.getClickedInventory().getContents()) {
                    if (is.getType().equals(Material.BLACKSTONE_WALL)) {
                        count++;
                    }
                }
            }
            //e.setCancelled(true);
        }
    }

}
