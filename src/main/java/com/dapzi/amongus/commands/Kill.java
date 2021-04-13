package com.dapzi.amongus.commands;

import com.dapzi.amongus.events.OnServerJoin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kill implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("kill")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage("You used this command");
                player.setHealth(0);
            }
            return true;
        }
        return false;
    }
}
