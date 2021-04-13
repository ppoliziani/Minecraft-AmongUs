package com.dapzi.amongus.commands;

import com.dapzi.amongus.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.Arrays;

public class Doors implements CommandExecutor {

    private BukkitTask doorTask = null;
    private final Main plugin;

    public Doors(Main plugin) { this.plugin = plugin; }

    private boolean open = true;

    public void openDoors(Player player, ArrayList<Location> doorloc) {
        player.sendMessage("Doors open");
        for (Location loc : doorloc) {
            loc.getBlock().setType(Material.AIR);
        }
        open = true;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("doors")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                //create more locations for each room
                ArrayList<Location> cafeDoorLocations = new ArrayList<Location>(Arrays.asList(
                        new Location(player.getWorld(), -17, 71, 188),
                        new Location(player.getWorld(), -27, 71, 180),
                        new Location(player.getWorld(), -35, 71, 188)));

                if (open) {
                    player.sendMessage("Doors closed");
                    for (Location loc : cafeDoorLocations) {
                        loc.getBlock().setType(Material.REDSTONE_BLOCK);
                    }
                    open = false;
                }
                Bukkit.getScheduler().runTaskLater(plugin, () -> openDoors(player, cafeDoorLocations), 10 * 20);
            }
            return true;
        }
        return false;
    }
}
