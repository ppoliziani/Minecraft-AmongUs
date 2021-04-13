package com.dapzi.amongus.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.block.data.type.TrapDoor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnVent implements Listener {

    public void venting(Player player, String room, Location location) {
        player.sendMessage(ChatColor.GREEN + "You clicked " + room);
        player.teleport(location);
    }

    @EventHandler
    public void onVent(PlayerInteractEvent event) {

        //add check that the player is an imposter otherwise do nothing

        //Player player = event.getPlayer();
        //event.getPlayer().sendMessage("You made and interaction");
        Action action = event.getAction();
        Block signBlock = event.getClickedBlock();

        Location trapdoorLocation;

        Player player = event.getPlayer();

        double x = player.getLocation().getX();
        double y = player.getLocation().getY();
        double z = player.getLocation().getZ();

        // signBlock here is actually a trap door
        // server resource pack has Cut_RED_SANDSTONE_SLAB as a vent teture
        if (action == Action.RIGHT_CLICK_BLOCK && signBlock.getType().equals(Material.CUT_RED_SANDSTONE_SLAB)) {
            trapdoorLocation = signBlock.getLocation();

            trapdoorLocation.setY(trapdoorLocation.getY() - 3);
            trapdoorLocation.setX(trapdoorLocation.getX() + 0.5);
            trapdoorLocation.setZ(trapdoorLocation.getZ() + 0.5);

            player.sendMessage(trapdoorLocation.toString());
            //player.teleport(new Location(player.getWorld(), x, y - 3, z));
            player.teleport(trapdoorLocation);
        }

        if (action == Action.RIGHT_CLICK_BLOCK && signBlock.getState() instanceof Sign) {
            player.sendMessage("YOU CLICKED");
            Sign sign = (Sign) signBlock.getState();
            String room = sign.getLine(0);
            player.sendMessage(room);

            switch (room) {
                case "ADMIN":
                    //player.sendMessage("You clicked admin");
                    //player.teleport(new Location(player.getWorld(), x, y + 1, z));
                    venting(player, "Admin", new Location(player.getWorld(), x, y + 10, z));
                    break;
                case "NAVIGATION HALL":
                    //player.sendMessage("You clicked navigation");
                    //player.teleport(new Location(player.getWorld(), x, y + 1, z));
                    venting(player, "Navigation Hall", new Location(player.getWorld(), x, y + 10, z));
                    break;
                case "NAVIGATION TOP":
                    venting(player, "Navigation Top", new Location(player.getWorld(), x, y + 10, z));
                    break;
                case "NAVIGATION BOTTOM":
                    venting(player, "Navigation Bottom", new Location(player.getWorld(), x, y + 10, z));
                    break;
                case "WEAPONS":
                    venting(player, "Weapons", new Location(player.getWorld(), x, y + 10, z));
                    break;
                case "SHIELDS":
                    venting(player, "Shields", new Location(player.getWorld(), x, y + 10, z));
                    break;
                case "MEDBAY":
                    venting(player, "Medbay ", new Location(player.getWorld(), x, y + 10, z));
                    break;
                case "ELECTRICAL":
                    venting(player, "Electrical ", new Location(player.getWorld(), x, y + 10, z));
                    break;
                case "SECURITY":
                    venting(player, "Security ", new Location(player.getWorld(), x, y + 10, z));
                    break;
                case "UPPER ENGINE":
                    venting(player, "Upper Engine ", new Location(player.getWorld(), x, y + 10, z));
                    break;
                case "REACTOR TOP":
                    venting(player, "Reactor Top", new Location(player.getWorld(), x, y + 10, z));
                    break;
                case "LOWER ENGINE":
                    venting(player, "Lower Engine", new Location(player.getWorld(), x, y + 10, z));
                    break;
                case "REACTOR BOTTOM":
                    venting(player, "Reactor Bottom", new Location(player.getWorld(), x, y + 10, z));
                    break;
                case "EXIT":
                    //player.sendMessage("You clicked exit");
                    //player.teleport(new Location(player.getWorld(), x, y + 1, z));
                    venting(player, "Entered room", new Location(player.getWorld(), x, y + 10, z));
                    break;
            }
        }

    }
}
