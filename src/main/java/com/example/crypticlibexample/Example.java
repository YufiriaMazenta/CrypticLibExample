package com.example.crypticlibexample;

import crypticlib.BukkitPlugin;
import crypticlib.command.api.CommandInfo;
import crypticlib.command.impl.RootCmdExecutor;
import crypticlib.util.MsgUtil;

import static crypticlib.command.CommandManager.subcommand;

public class Example extends BukkitPlugin {

    @Override
    public void enable() {
        saveDefaultConfig();
        new RootCmdExecutor()
            .setExecutor((sender, args) -> {
                MsgUtil.sendMsg(sender, "Hello, CrypticLib!");
                return true;
            })
            .regSub(subcommand("test")
                .setExecutor((sender, args) -> {
                    MsgUtil.sendMsg(sender, "CrypticLib Test!");
                    return true;
                })
                .regSub("test", (sender, args) -> {
                    MsgUtil.sendMsg(sender, "CrypticLib Test Test!");
                    return true;
                })
                .setPermission("example.command.test"))
            .regSub("test2", (sender, args) -> {
                MsgUtil.sendMsg(sender, "CrypticLib Test2!");
                return true;
            })
            .register(
                this,
                new CommandInfo("example", "example.command", new String[]{"exa"}, "Example command", "/example or /exa")
            );
    }

    @Override
    public void disable() {
        //TODO
    }

}