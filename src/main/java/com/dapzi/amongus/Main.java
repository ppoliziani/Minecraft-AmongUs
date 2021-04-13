package com.dapzi.amongus;

import com.dapzi.amongus.commands.*;
import com.dapzi.amongus.events.*;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;
import java.util.Arrays;

public final class Main extends JavaPlugin {

    //Every player gets both
    public static ArrayList<String> commonTasks = new ArrayList<String>(Arrays.asList("Swipe Card", "Fix Wiring"));

    //Every player gets 4 of these
    public static ArrayList<String> shortTasks = new ArrayList<String>(Arrays.asList("Align Engine", "Calibrate Distributor",
            "Clean O2", "Clear Asteroids", "Prime shields", "Stabilize Steering", "Unlock Manifolds"));

    //Every player gets 2 of these
    public static ArrayList<String> longTasks = new ArrayList<String>(Arrays.asList("Upload Data", "Submit Scan",
            "Start Reactor", "Inspect Sample", "Fuel Engines"));

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("S\nE\nR\nV\nE\nR");

        getCommand("test").setExecutor(new Test());
        getCommand("listp").setExecutor(new ListPlayers());
        getCommand("kill").setExecutor(new Kill());
        getCommand("start").setExecutor(new Start());
        getCommand("lights").setExecutor(new Lights(this));
        getCommand("doors").setExecutor(new Doors(this));
        getCommand("gui").setExecutor(new GUI(this));

        //getServer().getPluginManager().registerEvents(new OnMove(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new OnReport(), this);
        getServer().getPluginManager().registerEvents(new OnServerJoin(), this);
        getServer().getPluginManager().registerEvents(new OnVent(), this);
        getServer().getPluginManager().registerEvents(new OnHunger(), this);
        getServer().getPluginManager().registerEvents(new OnInventoryClick(), this);
        getServer().getPluginManager().registerEvents(new OnSwipeCard(this), this);
        getServer().getPluginManager().registerEvents(new OnScan(this), this);
        getServer().getPluginManager().registerEvents(new OnCleanO2(), this);



    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    //voting can be done in a runnable
    //whole game in a runnable??


}
