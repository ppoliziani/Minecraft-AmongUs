package com.dapzi.amongus.events;

import com.dapzi.amongus.Main;
import com.dapzi.amongus.tasks.ScanLoadingTask;
import com.dapzi.amongus.tasks.SwipeCardTask;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

public class OnScan implements Listener {

    private Main plugin;
    BukkitTask scanLoadingTask = null;

    public OnScan(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onSubmitScan(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        Block block = event.getClickedBlock();

        if (action == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType().equals(Material.NETHER_GOLD_ORE)) {
            //this.scanTask = new SubmitScanTask(plugin, player, block).runTaskTimer(plugin, 0, 10 * 20);
            player.sendMessage("SCANNING");
            player.teleport(new Location(player.getWorld(),
                    block.getLocation().getX() + 0.5,
                    block.getLocation().getY() + 1,
                    block.getLocation().getZ() + 0.5));
            player.setWalkSpeed(0);
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS , 5 * 20, 9999));
            Inventory scanGUI = Bukkit.createInventory(player, 9, "Submitting Scan");
            player.openInventory(scanGUI);

            this.scanLoadingTask = new ScanLoadingTask(plugin, player, scanGUI, 0)
                    .runTaskTimer(plugin, 40, 40);



            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                @Override
                public void run() {
                    player.closeInventory();
                    player.setWalkSpeed(0.2f);
                }
            }, 20 * 20);

        }
    }
}
