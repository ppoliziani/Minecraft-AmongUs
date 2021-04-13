package com.dapzi.amongus.commands;

import com.dapzi.amongus.Main;
import com.dapzi.amongus.events.OnServerJoin;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
// import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.*;
import sun.tools.jconsole.inspector.XObject;

import java.lang.reflect.Array;
import java.util.*;

public class Start implements CommandExecutor {

    ArrayList<Color> colours = new ArrayList<Color>(Arrays.asList(Color.PURPLE, Color.BLUE, Color.BLACK, Color.YELLOW,
            Color.GREEN, Color.LIME, Color.AQUA, Color.ORANGE, Color.RED, Color.WHITE));

    ArrayList<Material> correspondingWoolBLocks = new ArrayList<Material>(Arrays.asList(Material.PURPLE_WOOL,
            Material.BLUE_WOOL, Material.BLACK_WOOL, Material.YELLOW_WOOL, Material.GREEN_WOOL, Material.LIME_WOOL,
            Material.LIGHT_BLUE_WOOL, Material.ORANGE_WOOL, Material.RED_WOOL, Material.WHITE_WOOL));

    Random rand = new Random();

    Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

    public void updateTasks(Player player) {
        System.out.println("Test");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("start")) {
            if (sender instanceof Player) {
                sender.sendMessage("STARTING");
                for (String player : OnServerJoin.playerMap.values()) {
                    Bukkit.getServer().broadcastMessage(player);
                    Player p = Bukkit.getPlayer(player);
                    p.setWalkSpeed(0.2f);

                    //Assigning each player a team to then hide nametags
                    //Team team = board.registerNewTeam(p.getDisplayName());
                    //assert team != null;
                    //team.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);

                    //p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION , 30 * 20, 1));

                    /// DEALING WITH GIVING PLAYERS TASKS
                    HashMap<UUID, ArrayList<String>> playerTasks = new HashMap<UUID, ArrayList<String>>();

                    ArrayList<String> l = new ArrayList<String>();
                    for (String cTask : Main.commonTasks) {
                        l.add(cTask);
                    }

                    int task1 = rand.nextInt(Main.shortTasks.size());
                    int task2 = rand.nextInt(Main.shortTasks.size());
                    while (task2 == task1) {
                        task2 = rand.nextInt(Main.shortTasks.size());
                    }
                    int task3 = rand.nextInt(Main.shortTasks.size());
                    while (task3 == task2 || task3 == task1) {
                        task3 = rand.nextInt(Main.shortTasks.size());
                    }
                    int task4 = rand.nextInt(Main.shortTasks.size());
                    while (task4 == task3 || task4 == task2 || task4 == task1) {
                        task4 = rand.nextInt(Main.shortTasks.size());
                    }

                    l.add(Main.shortTasks.get(task1));
                    l.add(Main.shortTasks.get(task2));
                    l.add(Main.shortTasks.get(task3));
                    l.add(Main.shortTasks.get(task4));

                    int longtask1 = rand.nextInt(Main.longTasks.size());
                    int longtask2 = rand.nextInt(Main.longTasks.size());
                    while (longtask2 == longtask1) {
                        longtask2 = rand.nextInt(Main.longTasks.size());
                    }

                    l.add(Main.longTasks.get(longtask1));
                    l.add(Main.longTasks.get(longtask2));

                    playerTasks.put(p.getUniqueId(), l);

                    p.sendMessage(ChatColor.BLUE + l.toString());
                    /// DEALING WITH GIVING PLAYERS TASKS


                    p.sendMessage("YOU ARE A PLAYER");
                    double x = p.getLocation().getX();
                    double y = p.getLocation().getY();
                    double z = p.getLocation().getZ();
                    p.teleport(new Location(p.getWorld(), x - 1, y, z - 1));

                    // GIVING PLAYERS NEW SET OF COLOURED LEATHER ARMOR TO MIMICK COLOURS IN AMONG US
                    ItemStack[] armourSet = new ItemStack[]{new ItemStack(Material.LEATHER_BOOTS),
                            new ItemStack(Material.LEATHER_LEGGINGS), new ItemStack(Material.LEATHER_CHESTPLATE),
                            new ItemStack(Material.LEATHER_HELMET)};

                    int index = rand.nextInt(colours.size());
                    sender.sendMessage("list size: " + colours.size());
                    Color c = colours.get(index);
                    p.sendMessage(ChatColor.GOLD + "You are " + c.toString());
                    for (ItemStack item : armourSet) {
                        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
                        meta.setColor(c);
                        item.setItemMeta(meta);
                    }

                    ItemStack itemToShowColour = new ItemStack(Material.LEATHER_HELMET);
                    LeatherArmorMeta meta = (LeatherArmorMeta) itemToShowColour.getItemMeta();
                    meta.setColor(c);
                    itemToShowColour.setItemMeta(meta);
                    p.getInventory().setItem(4, itemToShowColour);
                    p.getInventory().setItem(5, new ItemStack(correspondingWoolBLocks.get(index)));
                    // p.getInventory().setStorageContents(new ItemStack[]{new ItemStack(correspondingWoolBLocks.get(index))});

                    colours.remove(index);
                    correspondingWoolBLocks.remove(index);
                    sender.sendMessage("list size after changing armor colour: " + colours.size());

                    p.getInventory().setArmorContents(armourSet);

                    // create scoreboard showing players their tasks - and remove tasks when they've completed them
                    ScoreboardManager manager = Bukkit.getScoreboardManager();
                    Scoreboard board = manager.getNewScoreboard();
                    Objective obj = board.registerNewObjective("Tasks", "dummy");
                    obj.setDisplayName("Tasks");
                    obj.setDisplaySlot(DisplaySlot.SIDEBAR);
                    for (String t : l) {
                        Score score = obj.getScore(t);
                        score.setScore(1);
                    }
                    p.setScoreboard(board);

                    for (String name : OnServerJoin.playerMap.values()) {
                        if (name.equals("0Blazin_Cheif0") | name.equals("d_apzi")) {
                            Bukkit.getPlayer(name).sendMessage(ChatColor.RED + "You are an imposter");
                            Bukkit.getPlayer(name).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1200, 9999));
                        }
                    }
                }

                /*
                Player player = (Player) sender;
                for (Team t : board.getTeams()) {
                    player.sendMessage(t.getDisplayName());
                }
                 */
            }
            return true;
        }
        return false;
    }
}
