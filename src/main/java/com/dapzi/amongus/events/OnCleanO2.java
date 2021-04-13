package com.dapzi.amongus.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Random;


public class OnCleanO2 implements Listener {
    @EventHandler
    public void onCleanO2(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Action action = e.getAction();
        Block block = e.getClickedBlock();

        Random rand = new Random();

        if (action == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType().equals(Material.VINE)) {
            Inventory o2GUI = Bukkit.createInventory(player, 27, "O2");
            for (int x = 0; x < 27; x++) {
                int pos = rand.nextInt(26);

                if (x < 2) {
                    o2GUI.setItem(pos, new ItemStack(Material.KELP));
                } else if (x >= 2 && x < 4) {
                    o2GUI.setItem(pos, new ItemStack(Material.FERN));
                } else {
                    o2GUI.setItem(pos, new ItemStack(Material.OAK_SAPLING));
                }
            }
            player.openInventory(o2GUI);
        }

    }
}