package com.example.crypticlibexample;

import crypticlib.BukkitPlugin;
import crypticlib.command.BukkitCommand;
import crypticlib.command.BukkitSubcommand;
import crypticlib.command.CommandInfo;
import crypticlib.listener.EventListener;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@EventListener
public class Example extends BukkitPlugin implements Listener {

    public static Example INSTANCE;

    @Override
    public void enable() {
        INSTANCE = this;
        System.out.println(ExampleConfig.test.value());
        System.out.println(ExampleConfig.test2.value());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        System.out.println("Online Players:" + Bukkit.getOnlinePlayers().size());
    }

    @Override
    public void disable() {
        //TODO
    }

}