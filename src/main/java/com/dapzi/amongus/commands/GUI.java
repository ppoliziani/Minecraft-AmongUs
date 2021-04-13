package com.dapzi.amongus.commands;

import com.dapzi.amongus.Main;
import com.dapzi.amongus.tasks.SwipeCardTask;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GUI implements CommandExecutor {

    private final Main plugin;
    private BukkitTask swipeTask = null;

    public GUI(Main plugin) { this.plugin = plugin; }

    Random rand = new Random();

    /*
    public void asteroids(Inventory gui) {
        int index = rand.nextInt(36);
        gui.setItem(index, new ItemStack(Material.ACACIA_BOAT));
    }
     */


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("gui")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                Inventory gui = Bukkit.createInventory(player, 9, "Swipe Card");

                gui.setContents(new ItemStack[]{new ItemStack(Material.STONE)});

                //int index = rand.nextInt(36);
                gui.setItem(0, new ItemStack(Material.QUARTZ_BLOCK, 9));

                player.openInventory(gui);

                this.swipeTask = new SwipeCardTask(plugin, player, gui).runTaskTimer(plugin, 0, 10);

                /*
                for (int x = 0; x < 20; x++) {
                    Bukkit.getScheduler().runTaskLater(plugin, () -> asteroids(gui), 1 * 20);
                }
                 */

            }
            return true;
        }
        return false;
    }
}
