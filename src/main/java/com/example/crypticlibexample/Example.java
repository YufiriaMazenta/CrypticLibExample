package com.example.crypticlibexample;

import crypticlib.BukkitPlugin;
import crypticlib.CrypticLib;
import crypticlib.command.CommandInfo;
import crypticlib.command.RootCmdExecutor;
import crypticlib.util.MsgUtil;

import static crypticlib.command.CommandManager.subcommand;
import static crypticlib.command.CommandManager.rootCommand;

public class Example extends BukkitPlugin {

    @Override
    public void enable() {
        saveDefaultConfig();
        CrypticLib.commandManager().register(
                this,
                new CommandInfo("example", "example.command", new String[]{"exa"}, "Example command", "/example or /exa"),
                (RootCmdExecutor) rootCommand()
                        .setExecutor((sender, args) -> {
                            MsgUtil.sendMsg(sender, "Hello, CrypticLib!");
                            return true;
                        })
                        .regSub(subcommand("test")
                                        .setExecutor((sender, args) -> {
                                            MsgUtil.sendMsg(sender, "CrypticLib Test!");
                                            return true;
                                        }))
                        .regSub("test2", (sender, args) -> {
                            MsgUtil.sendMsg(sender, "CrypticLib Test2!");
                            return true;
                        })
        );
    }

    @Override
    public void disable() {
        //TODO
    }

}