package com.example.crypticlibexample;

import crypticlib.BukkitPlugin;
import org.bukkit.Bukkit;

public class Example extends BukkitPlugin {

    public static Example INSTANCE;

    @Override
    public void enable() {
        INSTANCE = this;
    }
    @Override
    public void disable() {
        //TODO
    }

}