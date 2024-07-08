package com.example.crypticlibexample.bungee;

import crypticlib.BungeePlugin;
import crypticlib.BungeeVersion;
import net.md_5.bungee.api.ProxyServer;

public class Example extends BungeePlugin {

    public static Example INSTANCE;

    @Override
    public void enable() {
        INSTANCE = this;
        System.out.println(BungeeVersion.current().version());
    }

    @Override
    public void disable() {
        //TODO
    }

}