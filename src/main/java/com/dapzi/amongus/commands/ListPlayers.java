package com.dapzi.amongus.commands;

import com.dapzi.amongus.events.OnServerJoin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListPlayers implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("listp")) {
            if (sender instanceof Player) {
                sender.getServer().broadcastMessage(String.valueOf(OnServerJoin.playerMap.values()));
            }
            return true;
        }
        return false;
    }
}
