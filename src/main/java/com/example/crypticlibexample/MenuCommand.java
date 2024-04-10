package com.example.crypticlibexample;

import crypticlib.command.CommandHandler;
import crypticlib.command.CommandInfo;
import crypticlib.command.annotation.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

@Command
public class MenuCommand extends CommandHandler {

    public MenuCommand() {
        super(new CommandInfo("menu"));
    }

    @Override
    public boolean execute(CommandSender sender, List<String> args) {
        new TestMenu((Player) sender).openMenu();
        return true;
    }
}
