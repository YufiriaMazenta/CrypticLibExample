package com.example.crypticlibexample;

import crypticlib.BukkitPlugin;
import crypticlib.CrypticLibBukkit;
import crypticlib.MinecraftVersion;
import crypticlib.listener.EventListener;
import crypticlib.util.IOHelper;
import org.bukkit.event.Listener;

@EventListener
public class Example extends BukkitPlugin implements Listener {

    public static Example INSTANCE;

    public Example() {
        INSTANCE = this;
    }

    @Override
    public void whenEnable() {
        IOHelper.info("Server Type: " + CrypticLibBukkit.serverAdapter().type().name());
        IOHelper.info("Server Version: " + MinecraftVersion.CURRENT.name());
    }

    @Override
    public void whenDisable() {
        IOHelper.info("Plugin disabled.");
    }

}