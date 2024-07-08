package com.example.crypticlibexample.bungee;

import crypticlib.BungeePlugin;

public class Example extends BungeePlugin {

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