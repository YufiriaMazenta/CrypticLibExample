package com.example.crypticlibexample.cmd;

import com.example.crypticlibexample.Example;
import crypticlib.annotations.BukkitCommand;
import crypticlib.command.IPluginCmdExecutor;
import crypticlib.command.ISubCmdExecutor;
import crypticlib.util.MsgUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@BukkitCommand(
        name = "example",
        permission = "example.command",
        alias = "exa",
        usage = "/example or /exa",
        description = "Example command"
)
public class ExampleCmd implements IPluginCmdExecutor {

    @Override
    public Plugin getPlugin() {
        return Example.instance();
    }

    @Override
    public @NotNull Map<String, ISubCmdExecutor> subCommands() {
        return new HashMap<>();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        MsgUtil.sendMsg(sender, "Hello, CrypticLib!");
        return true;
    }

}
