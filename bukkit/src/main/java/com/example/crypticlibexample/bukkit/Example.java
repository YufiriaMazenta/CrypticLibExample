package com.example.crypticlibexample.bukkit;

import crypticlib.BukkitPlugin;

public class Example extends BukkitPlugin {

    public static Example INSTANCE;

    @Override
    public void enable() {
        INSTANCE = this;
        System.out.println(ExampleConfig.test.value());
        System.out.println(ExampleConfig.test2.value());
    }

    @Override
    public void disable() {
        //TODO
    }

}