package com.dapzi.amongus.commands;

import com.dapzi.amongus.Main;
import com.dapzi.amongus.events.OnServerJoin;
import com.dapzi.amongus.tasks.LightTask;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

public class Lights implements CommandExecutor {

    private BukkitTask lightTask = null;
    private final Main plugin;

    public Lights(Main plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("lights")) {
            if (sender instanceof Player) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    //Player player = Bukkit.getPlayer(name);
                    //duration is in ticks, 20 = 1 second
                    //change to 6000
                    player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS , 30 * 20, 9999));

                }
                // start runnable to check lever is down and cancel blindness
                Player p = (Player) sender;
                this.lightTask = new LightTask(plugin, p).runTaskTimer(plugin, 0, 10);
            }
            return true;
        }
        return false;
    }
}
